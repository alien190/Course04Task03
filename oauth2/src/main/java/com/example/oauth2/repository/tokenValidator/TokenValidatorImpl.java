package com.example.oauth2.repository.tokenValidator;


import android.arch.lifecycle.MutableLiveData;

import com.example.oauth2.repository.gitHubRepository.IGHRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;


public class TokenValidatorImpl implements ITokenValidator {

    private String mToken = "";
    private MutableLiveData<Integer> mTokenState = new MutableLiveData<>();

    private IGHRepository mIGHRepository;

    private Lock mTokenLock = new ReentrantLock();


    public TokenValidatorImpl(IGHRepository ghRepository) {

        this.mIGHRepository = ghRepository;

        mTokenState.postValue(ITokenValidator.TOKEN_IN_PROGRESS);

        obtainToken();
    }

    public void obtainToken() {
        mTokenState.postValue(TOKEN_EMPTY);
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
                            mTokenState.postValue(ITokenValidator.TOKEN_CREATED);
                            validateToken(token);
                        },
                        throwable -> {
                            mTokenState.postValue(ITokenValidator.TOKEN_CREATION_ERROR);
                            Timber.e(throwable);
                        });

    }
}
