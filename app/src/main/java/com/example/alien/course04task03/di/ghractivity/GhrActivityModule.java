package com.example.alien.course04task03.di.ghractivity;

import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.ui.gHR.GhrFragment;
import com.example.alien.course04task03.ui.gHR.GhrViewModelFactory;
import com.example.alien.course04task03.ui.gHR.IGhrViewModel;

import toothpick.config.Module;

public class GhrActivityModule extends Module {
    private AppCompatActivity mAppCompatActivity;

    public GhrActivityModule(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    public GhrActivityModule() {
        bind(GhrFragment.class).toInstance(GhrFragment.newInstance());
        bind(AppCompatActivity.class).toInstance(mAppCompatActivity);
        bind(IGhrViewModel.class).toProvider(GhrViewModelProvider.class).providesSingletonInScope();
        bind(GhrViewModelFactory.class).toProvider(GhrViewModelFactoryPovider.class).providesSingletonInScope();
    }
}
