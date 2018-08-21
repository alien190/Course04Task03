package com.example.alien.course04task03.repository.tokenValidator;


import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class TokenValidatorImpl implements ITokenValidator {

    private String mToken = "";
    private MutableLiveData<Integer> mTokenState = new MutableLiveData<>();

    private ISharedPref mSharedPref;
    private IGHRepository mIGHRepository;

    private Lock mTokenLock = new ReentrantLock();


    public TokenValidatorImpl(ISharedPref sharedPref, IGHRepository ghRepository) {
        this.mSharedPref = sharedPref;
        this.mIGHRepository = ghRepository;

        mTokenState.postValue(ITokenValidator.TOKEN_IN_PROGRESS);

        obtainToken();
    }

    public void obtainToken() {

        try {
            //mTokenLock.lock();

            Disposable disposable = mSharedPref.readToken()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(value -> {
                        if (value.isEmpty()) {
                            mTokenState.postValue(TOKEN_EMPTY);
                            // mTokenLock.unlock();
                        } else {
                            validateToken(value);
                        }
                    }, throwable -> {
                        mTokenState.postValue(TOKEN_EMPTY);
                        Timber.e(throwable);
                        //mTokenLock.unlock();
                    });

        } catch (Throwable t) {
            Timber.e(t);
            // mTokenLock.unlock();
        }
    }


    @Override
    public String getToken() {
        return mToken;
    }

    @Override
    public MutableLiveData<Integer> getTokenState() {
        return mTokenState;
    }

    @Override
    public void validateToken(String token) {
        try {

            mToken = token;
            mIGHRepository.setAuthHeaderToken(null);
            Disposable disposable = mIGHRepository.validateToken(token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isTokenValid -> {
                                mTokenState.postValue(isTokenValid ? TOKEN_VALID : TOKEN_INVALID);
                                // mTokenLock.unlock();
                            },
                            throwable -> {
                                mTokenState.postValue(TOKEN_VALIDATION_ERROR);
                                Timber.e(throwable);
                                // mTokenLock.unlock();
                            }
                    );
        } catch (Throwable t) {
            Timber.e(t);
            // mTokenLock.unlock();
        }
    }

    @Override
    public void createToken(String code, String clientId, String clientSecret) {
        Disposable disposable = mIGHRepository.createToken(code, clientId, clientSecret)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(token -> {
                            mToken = token;
                            mSharedPref.writeToken(token);
                            mTokenState.postValue(ITokenValidator.TOKEN_CREATED);
                            validateToken(token);
                        },
                        throwable -> {
                            mTokenState.postValue(ITokenValidator.TOKEN_CREATION_ERROR);
                            Timber.e(throwable);
                        });

    }
}
