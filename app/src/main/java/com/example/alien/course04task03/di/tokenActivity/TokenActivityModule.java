package com.example.alien.course04task03.di.tokenActivity;


import com.example.oauth2.token.TokenFragment;

import toothpick.config.Module;

public class TokenActivityModule extends Module {

    private String mScopeName;
    private String mToken;

    public TokenActivityModule(String scopeName, String token) {

        this.mScopeName = scopeName;
        this.mToken = token;
        bind(TokenFragment.class).toInstance(TokenFragment.newInstance(mToken, mScopeName));
    }
}
