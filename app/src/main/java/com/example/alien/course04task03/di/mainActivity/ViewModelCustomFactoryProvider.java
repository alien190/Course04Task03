package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelCustomFactoryProvider implements Provider<ViewModelCustomFactory> {

    protected IRepoRepository mRepository;
    private Gson mGson;

    @Inject
    public ViewModelCustomFactoryProvider(IRepoRepository mRepository, Gson gson) {
        this.mRepository = mRepository;
        this.mGson = gson;
    }

    @Override
    public ViewModelCustomFactory get() {
        return new ViewModelCustomFactory(mRepository, mGson);
    }
}
