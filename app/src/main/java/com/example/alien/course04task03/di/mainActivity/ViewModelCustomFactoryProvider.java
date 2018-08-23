package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class ViewModelCustomFactoryProvider implements Provider<ViewModelCustomFactory> {

    protected IGitHubRepository mRepository;

    @Inject
    public ViewModelCustomFactoryProvider(@Named("REMOTE") IGitHubRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public ViewModelCustomFactory get() {
        return new ViewModelCustomFactory(mRepository);
    }
}
