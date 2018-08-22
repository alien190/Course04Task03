package com.example.oauth2.di;


import com.example.oauth2.api.IAuthApi;
import com.example.oauth2.repository.gitHubRepository.GHRepositoryImpl;
import com.example.oauth2.repository.gitHubRepository.IGHRepository;

import javax.inject.Inject;
import javax.inject.Provider;

class GHRepositoryProvider implements Provider<IGHRepository> {

    private IAuthApi mIAuthApi;

    @Inject
    public GHRepositoryProvider(IAuthApi iAuthApi) {
        this.mIAuthApi = iAuthApi;
    }

    @Override
    public IGHRepository get() {
        return new GHRepositoryImpl(mIAuthApi);
    }
}
