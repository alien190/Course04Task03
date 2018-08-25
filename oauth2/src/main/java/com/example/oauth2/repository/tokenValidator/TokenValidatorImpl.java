package com.example.oauth2.repository.tokenValidator;


import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.example.oauth2.repository.gitHubRepository.IGHRepository;
import com.example.oauth2.repository.tokenStorage.ITokenStorage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class TokenValidatorImpl implements ITokenValidator {
    private String mToken = "";
    private MutableLiveData<Integer> mTokenState = new MutableLiveData<>();
    private IGHRepository mIGHRepository;
    private ITokenStorage mITokenStorage;
    private Lock mTokenLock = new ReentrantLock();


    public TokenValidatorImpl(IGHRepository ghRepository, ITokenStorage tokenStorage) {
        this.mIGHRepository = ghRepository;
        this.mITokenStorage = tokenStorage;
    }

    public void obtainToken() {

        try {
            //mTokenLock.lock();

            Disposable disposable = mITokenStorage.readToken()
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
    @SuppressLint("CheckResult")
    public void validateToken(String token) {
        try {
            mTokenState.postValue(ITokenValidator.TOKEN_IN_PROGRESS);
            mToken = token;
            mIGHRepository.validateToken(token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isTokenValid -> {
                                if (isTokenValid) {
                                    mITokenStorage.writeToken(token).
                                            subscribe(b -> mTokenState.postValue(TOKEN_VALID));
                                } else {
                                    mTokenState.postValue(TOKEN_INVALID);
                                }
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
    public void createToken(String code) {
        Disposable disposable = mIGHRepository.createToken(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(token -> {
                            mToken = token;
                            mTokenState.postValue(ITokenValidator.TOKEN_CREATED);
                            validateToken(token);
                        },
                        throwable -> {
                            mTokenState.postValue(ITokenValidator.TOKEN_CREATION_ERROR);
                            Timber.e(throwable);
                        });
    }

    @Override
    public void clearToken() {
        Disposable disposable = mITokenStorage.clearToken()
                .subscribe(r -> {}, Timber::e);
    }
}
