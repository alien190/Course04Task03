package com.example.alien.course04task03.ui.Token;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;

public class TokenViewModel extends ViewModel implements ITokenViewModel {
    MutableLiveData<Integer> mState = new MutableLiveData<>();
    ITokenValidator mITokenValidator;

    public TokenViewModel(ITokenValidator ITokenValidator) {
        mITokenValidator = ITokenValidator;
    }

    @Override
    public MutableLiveData<Integer> getState() {
        return null;
    }
}
