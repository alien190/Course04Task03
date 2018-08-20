package com.example.alien.course04task03.di.tokenActivity;

import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.ui.token.ITokenViewModel;
import com.example.alien.course04task03.ui.token.TokenFragment;
import com.example.alien.course04task03.ui.token.TokenViewModelFactory;

import toothpick.config.Module;

public class TokenActivityModule extends Module {
    private AppCompatActivity mAppCompatActivity;

    public TokenActivityModule(AppCompatActivity activity) {
        this.mAppCompatActivity = activity;
        bind(AppCompatActivity.class).toInstance(mAppCompatActivity);
        bind(ITokenViewModel.class).toProvider(TokenViewModelProvider.class).providesSingletonInScope();
        bind(TokenViewModelFactory.class).toProvider(TokenViewModelFactoryPovider.class).providesSingletonInScope();
        bind(TokenFragment.class).toInstance(TokenFragment.newInstance());
    }
}
