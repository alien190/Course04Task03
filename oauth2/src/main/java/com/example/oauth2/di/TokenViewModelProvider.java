package com.example.oauth2.di;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.example.oauth2.token.TokenViewModel;
import com.example.oauth2.token.TokenViewModelFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class TokenViewModelProvider implements Provider<TokenViewModel> {
    Fragment mFragment;
    TokenViewModelFactory mTokenViewModelFactory;

    @Inject
    public TokenViewModelProvider(Fragment fragment, TokenViewModelFactory tokenViewModelFactory) {
        this.mFragment = fragment;
        this.mTokenViewModelFactory = tokenViewModelFactory;
    }

    @Override
    public TokenViewModel get() {
        return ViewModelProviders.of(mFragment, mTokenViewModelFactory).get(TokenViewModel.class);
    }
}
