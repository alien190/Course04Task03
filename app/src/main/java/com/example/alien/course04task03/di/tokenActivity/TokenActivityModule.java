package com.example.alien.course04task03.di.tokenActivity;


import com.example.oauth2.ui.TokenFragment;

import toothpick.config.Module;

public class TokenActivityModule extends Module {

    private String mScopeName;
    private Boolean mDoLogout;

    public TokenActivityModule(String scopeName, Boolean doLogout) {

        this.mScopeName = scopeName;
        this.mDoLogout = doLogout;
        bind(TokenFragment.class).toInstance(TokenFragment.newInstance(mScopeName, doLogout));
    }
}
