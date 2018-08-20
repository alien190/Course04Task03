package com.example.alien.course04task03.di.tokenActivity;

import com.example.alien.course04task03.ui.token.TokenViewModelFactory;
import com.example.alien.course04task03.ui.token.TokenViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;
import javax.inject.Provider;

class TokenViewModelProvider implements Provider<TokenViewModel> {
    AppCompatActivity mAppCompatActivity;
    TokenViewModelFactory mTokenViewModelFactory;

    @Inject
    public TokenViewModelProvider(AppCompatActivity appCompatActivity, TokenViewModelFactory tokenViewModelFactory) {
        this.mAppCompatActivity = appCompatActivity;
        this.mTokenViewModelFactory = tokenViewModelFactory;
    }

    @Override
    public TokenViewModel get() {
        return ViewModelProviders.of(mAppCompatActivity, mTokenViewModelFactory).get(TokenViewModel.class);
    }
}
