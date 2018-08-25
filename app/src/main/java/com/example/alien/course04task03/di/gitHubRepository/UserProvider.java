package com.example.alien.course04task03.di.gitHubRepository;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

class UserProvider implements Provider<Single> {

    private IGitHubRepository mIGitHubRepository;

    @Inject
    public UserProvider(@Named("REMOTE") IGitHubRepository iGitHubRepository) {
        mIGitHubRepository = iGitHubRepository;
    }

    @Override
    public Single get() {
        return mIGitHubRepository.getUser().observeOn(Schedulers.io()).cache();
    }
}
