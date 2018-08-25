package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import io.reactivex.Single;

public class ViewModelCustomFactoryProvider implements Provider<ViewModelCustomFactory> {

    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;
    private Single<User> mUser;

    @Inject
    public ViewModelCustomFactoryProvider(@Named("REMOTE") IGitHubRepository remoteRepository,
                                          @Named("LOCAL") IGitHubRepository localRepository,
                                          @Named("USER") Single<User> user) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;
        this.mUser = user;
    }

    @Override
    public ViewModelCustomFactory get() {
        return new ViewModelCustomFactory(mRemoteRepository, mLocalRepository, mUser);
    }
}
