package com.example.alien.course04task03.di.mainActivity;

import android.app.Application;

import com.example.alien.course04task03.data.IGitHubDao;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;
import com.google.gson.Gson;

import toothpick.config.Module;

public class ApplicationModule extends Module{

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;

       // bind(IFilmRepository.class).toInstance(new RealmFilmRepository());
        bind(Gson.class).toInstance(new Gson());
        bind(ViewModelCustomFactory.class).toProvider(ViewModelCustomFactoryProvider.class).providesSingletonInScope();
        bind(Application.class).toInstance(mApplication);
        bind(IGitHubDao.class).toProvider(RepoDaoProvider.class).providesSingletonInScope();
    }
}
