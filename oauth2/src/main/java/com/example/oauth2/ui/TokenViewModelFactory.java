package com.example.oauth2.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import com.example.oauth2.repository.tokenValidator.ITokenValidator;

import javax.inject.Inject;

public class TokenViewModelFactory implements ViewModelProvider.Factory{
    ITokenValidator mITokenValidator;

    @Inject
    public TokenViewModelFactory(ITokenValidator ITokenValidator) {
        mITokenValidator = ITokenValidator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TokenViewModel(mITokenValidator);
    }
}
