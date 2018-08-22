package com.example.oauth2.di;

import android.support.v4.app.Fragment;

import com.example.oauth2.token.CustomWebViewClient;
import com.example.oauth2.token.ITokenViewModel;
import com.example.oauth2.token.TokenViewModelFactory;

import toothpick.config.Module;

public class TokenModule extends Module {
    private Fragment mFragment;

    public TokenModule(Fragment fragment) {
        this.mFragment = fragment;
        bind(Fragment.class).toInstance(mFragment);
        bind(ITokenViewModel.class).toProvider(TokenViewModelProvider.class).providesSingletonInScope();
        bind(TokenViewModelFactory.class).toProvider(TokenViewModelFactoryProvider.class).providesSingletonInScope();
        bind(CustomWebViewClient.class).toInstance(new CustomWebViewClient());
    }
}
