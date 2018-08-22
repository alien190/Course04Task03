package com.example.alien.course04task03.di.tokenActivity;


import com.example.oauth2.token.TokenFragment;

import toothpick.config.Module;

public class TokenActivityModule extends Module {

    private String mScopeName;

    public TokenActivityModule(String scopeName) {

        this.mScopeName = scopeName;
        bind(TokenFragment.class).toInstance(TokenFragment.newInstance(mScopeName));
    }
}
