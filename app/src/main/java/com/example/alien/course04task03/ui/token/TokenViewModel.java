package com.example.alien.course04task03.ui.token;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;

public class TokenViewModel extends ViewModel implements ITokenViewModel {
    private MutableLiveData<Integer> mState = new MutableLiveData<>();
    private ITokenValidator mTokenValidator;
    private Observer<Integer> mStateObserver = state -> {
        switch (state) {
            case  ITokenValidator.TOKEN_IN_PROGRESS:
            {
                mState.postValue(ITokenViewModel.STATE_SPLASH);
                break;
            }
            case  ITokenValidator.TOKEN_EMPTY:
            {
                mState.postValue(ITokenViewModel.STATE_AUTH);
                break;
            }
            case  ITokenValidator.TOKEN_VALID:
            {
                mState.postValue(ITokenViewModel.STATE_COMPLETE);
                break;
            }
            case  ITokenValidator.TOKEN_INVALID:
            {
                mState.postValue(ITokenViewModel.STATE_AUTH);
                break;
            }
            case  ITokenValidator.TOKEN_VALIDATION_ERROR:
            {
                mState.postValue(ITokenViewModel.STATE_AUTH_INTERACTIVE);
                break;
            }
            case  ITokenValidator.TOKEN_CREATION_ERROR:
            {
                mState.postValue(ITokenViewModel.STATE_AUTH);
                break;
            }

            default: {
               // mState.postValue(ITokenViewModel.STATE_SPLASH );
                break;
            }
        }
    };

    public TokenViewModel(ITokenValidator TokenValidator) {
        mTokenValidator = TokenValidator;

        mTokenValidator.getTokenState().observeForever(mStateObserver);
    }

    @Override
    public MutableLiveData<Integer> getState() {
        return mState;
    }

    @Override
    protected void onCleared() {
        mTokenValidator.getTokenState().removeObserver(mStateObserver);
        super.onCleared();
    }

    @Override
    public void createToken(String code, String clientId, String clientSecret) {
        mTokenValidator.createToken(code, clientId, clientSecret);
    }

    @Override
    public void showAuthorizationForm() {
        mState.postValue(ITokenViewModel.STATE_SHOW_AUTH);
    }

    @Override
    public void startNewAuth() {
        mState.postValue(ITokenViewModel.STATE_SPLASH);
        mTokenValidator.obtainToken();
    }
}
