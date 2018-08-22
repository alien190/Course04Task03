package com.example.alien.course04task03.di.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;
import com.example.alien.course04task03.ui.search.SearchByDirectorViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByDirectorViewModelProvider implements Provider<SearchByDirectorViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByDirectorViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByDirectorViewModel.class);
    }
}