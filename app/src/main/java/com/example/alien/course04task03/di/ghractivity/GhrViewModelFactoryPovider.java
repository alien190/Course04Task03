package com.example.alien.course04task03.di.ghractivity;

import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;
import com.example.alien.course04task03.ui.gHR.GhrViewModelFactory;
import com.example.alien.course04task03.ui.token.TokenViewModelFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class GhrViewModelFactoryPovider implements Provider<GhrViewModelFactory> {
    private IGHRepository mIGHRepository;

    @Inject
    public GhrViewModelFactoryPovider(IGHRepository IGHRepository) {
        mIGHRepository = IGHRepository;
    }


    @Override
    public GhrViewModelFactory get() {
        return new GhrViewModelFactory(mIGHRepository);
    }
}
