package com.example.alien.course04task03.repository.tokenValidator;


import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Single;
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

    private void obtainToken() {

        try {
            mTokenLock.lock();

            Single<String> token = mSharedPref.readToken();

            token.observeOn(Schedulers.io())
                    .subscribe(value -> {
                        if (value.isEmpty()) {
                            mTokenState.postValue(TOKEN_EMPTY);
                            mTokenLock.unlock();
                        } else {
                            validateToken(value);
                        }
                    }, throwable -> {
                        mTokenState.postValue(TOKEN_EMPTY);
                        Timber.e(throwable);
                        mTokenLock.unlock();
                    });

        } catch (Throwable t) {
            Timber.e(t);
            mTokenLock.unlock();
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
            mIGHRepository.validateToken(token)
                    .observeOn(Schedulers.io())
                    .subscribe(isTokenValid -> {
                                mTokenState.postValue(isTokenValid ? TOKEN_VALID : TOKEN_INVALID);
                                mTokenLock.unlock();
                            },
                            throwable -> {
                                mTokenState.postValue(TOKEN_VALIDATION_ERROR);
                                Timber.e(throwable);
                                mTokenLock.unlock();
                            }
                    );
        } catch (Throwable t) {
            Timber.e(t);
            mTokenLock.unlock();
        }
    }
}
