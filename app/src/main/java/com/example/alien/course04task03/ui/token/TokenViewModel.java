package com.example.alien.course04task03.ui.token;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;

public class TokenViewModel extends ViewModel implements ITokenViewModel {
    private MutableLiveData<Integer> mState = new MutableLiveData<>();
    private ITokenValidator mITokenValidator;
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
                mState.postValue(ITokenViewModel.STATE_AUTH);
                break;
            }
            default: {
                mState.postValue(ITokenViewModel.STATE_SPLASH);
                break;
            }
        }
    };

    public TokenViewModel(ITokenValidator ITokenValidator) {
        mITokenValidator = ITokenValidator;

        mITokenValidator.getTokenState().observeForever(mStateObserver);
    }

    @Override
    public MutableLiveData<Integer> getState() {
        return mState;
    }

    @Override
    protected void onCleared() {
        mITokenValidator.getTokenState().removeObserver(mStateObserver);
        super.onCleared();
    }
}
