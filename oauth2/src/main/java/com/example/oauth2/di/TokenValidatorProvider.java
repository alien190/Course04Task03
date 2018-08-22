package com.example.oauth2.di;


import com.example.oauth2.repository.gitHubRepository.IGHRepository;
import com.example.oauth2.repository.tokenStorage.ITokenStorage;
import com.example.oauth2.repository.tokenValidator.ITokenValidator;
import com.example.oauth2.repository.tokenValidator.TokenValidatorImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class TokenValidatorProvider implements Provider<ITokenValidator>
{

    private IGHRepository mIGHRepository;
    private ITokenStorage mITokenStorage;

    @Inject
    public TokenValidatorProvider(IGHRepository IGHRepository, ITokenStorage tokenStorage) {
        mIGHRepository = IGHRepository;
        mITokenStorage = tokenStorage;
    }

    @Override
    public ITokenValidator get() {
        return new TokenValidatorImpl(mIGHRepository, mITokenStorage);
    }
}
