package com.example.alien.course04task03.di.ghractivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.ui.gHR.GhrViewModel;
import com.example.alien.course04task03.ui.gHR.GhrViewModelFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class GhrViewModelProvider implements Provider<GhrViewModel> {
    AppCompatActivity mAppCompatActivity;
    GhrViewModelFactory mGhrViewModelFactory;

    @Inject
    public GhrViewModelProvider(AppCompatActivity appCompatActivity, GhrViewModelFactory ghrViewModelFactory) {
        mAppCompatActivity = appCompatActivity;
        mGhrViewModelFactory = ghrViewModelFactory;
    }

    @Override
    public GhrViewModel get() {
        return ViewModelProviders.of(mAppCompatActivity, mGhrViewModelFactory).get(GhrViewModel.class);
    }
}
