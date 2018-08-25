package com.example.oauth2.di;

import com.example.oauth2.repository.tokenValidator.ITokenValidator;
import com.example.oauth2.ui.TokenViewModelFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class TokenViewModelFactoryProvider implements Provider<TokenViewModelFactory> {
    private ITokenValidator mITokenValidator;

    @Inject
    public TokenViewModelFactoryProvider(ITokenValidator ITokenValidator) {
        mITokenValidator = ITokenValidator;
    }

    @Override
    public TokenViewModelFactory get() {
        return new TokenViewModelFactory(mITokenValidator);
    }
}
