package com.example.alien.course04task03.di.gitHubRepository;

import com.example.alien.course04task03.api.HeaderInterceptor;
import com.example.oauth2.repository.tokenStorage.ITokenStorage;

import javax.inject.Inject;
import javax.inject.Provider;

public class HeaderInterceptorProvider implements Provider<HeaderInterceptor> {
    private ITokenStorage mITokenStorage;

    @Inject
    public HeaderInterceptorProvider(ITokenStorage tokenStorage) {
        this.mITokenStorage = tokenStorage;
    }

    @Override
    public HeaderInterceptor get() {
        return new HeaderInterceptor(mITokenStorage);
    }
}
