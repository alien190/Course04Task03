package com.example.alien.course04task03.di.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;
import com.example.alien.course04task03.ui.search.SearchByTopViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByTopViewModelProvider implements Provider<SearchByTopViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByTopViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByTopViewModel.class);
    }
}