package com.example.alien.course04task03.di.tokenActivity;

import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;
import com.example.alien.course04task03.ui.Token.TokenViewModelFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class TokenViewModelFactoryPovider implements Provider<TokenViewModelFactory> {
    private ITokenValidator mITokenValidator;

    @Inject
    public TokenViewModelFactoryPovider(ITokenValidator ITokenValidator) {
        mITokenValidator = ITokenValidator;
    }

    @Override
    public TokenViewModelFactory get() {
        return new TokenViewModelFactory(mITokenValidator);
    }
}
