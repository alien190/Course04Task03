package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class ViewModelCustomFactoryProvider implements Provider<ViewModelCustomFactory> {

    protected IGitHubRepository mRemoteRepository;
    protected IGitHubRepository mLocalRepository;

    @Inject
    public ViewModelCustomFactoryProvider(@Named("REMOTE") IGitHubRepository remoteRepository,
                                          @Named("LOCAL") IGitHubRepository localRepository) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;
    }

    @Override
    public ViewModelCustomFactory get() {
        return new ViewModelCustomFactory(mRemoteRepository, mLocalRepository);
    }
}
