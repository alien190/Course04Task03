package com.example.alien.course04task03.di.ghractivity;

import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;
import com.example.alien.course04task03.ui.gHR.GhrViewModelFactory;
import com.example.alien.course04task03.ui.token.TokenViewModelFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class GhrViewModelFactoryProvider implements Provider<GhrViewModelFactory> {
    private IGHRepository mIGHRepository;
    private ISharedPref mISharedPref;

    @Inject
    public GhrViewModelFactoryProvider(IGHRepository IGHRepository, ISharedPref iSharedPref) {
        mIGHRepository = IGHRepository;
        this.mISharedPref = iSharedPref;
    }


    @Override
    public GhrViewModelFactory get() {
        return new GhrViewModelFactory(mIGHRepository, mISharedPref);
    }
}
