package com.example.alien.course04task03.di;

import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;
import com.example.alien.course04task03.repository.tokenValidator.TokenValidatorImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class ITokenValidatorProvider implements Provider<ITokenValidator>
{
    private ISharedPref mISharedPref;
    private IGHRepository mIGHRepository;

    @Inject
    public ITokenValidatorProvider(ISharedPref ISharedPref, IGHRepository IGHRepository) {
        mISharedPref = ISharedPref;
        mIGHRepository = IGHRepository;
    }

    @Override
    public ITokenValidator get() {
        return new TokenValidatorImpl(mISharedPref, mIGHRepository);
    }
}
