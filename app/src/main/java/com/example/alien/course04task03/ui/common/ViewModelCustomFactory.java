package com.example.alien.course04task03.ui.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.ui.filmList.ListAllViewModel;
import com.example.alien.course04task03.ui.search.SearchByDirectorViewModel;
import com.example.alien.course04task03.ui.search.SearchByNameViewModel;
import com.example.alien.course04task03.ui.search.SearchByTopViewModel;
import com.example.alien.course04task03.ui.search.SearchByYearViewModel;
import com.google.gson.Gson;

public class ViewModelCustomFactory implements ViewModelProvider.Factory {
    private IRepoRepository mRepository;
    private Gson mGson;

    public ViewModelCustomFactory(IRepoRepository repository, Gson gson) {

        mRepository = repository;
        mGson = gson;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == SearchByNameViewModel.class) {
            return (T) new SearchByNameViewModel(mRepository, mGson);
        }
        if(modelClass == SearchByDirectorViewModel.class) {
            return (T) new SearchByDirectorViewModel(mRepository, mGson);
        }
        if(modelClass == SearchByYearViewModel.class) {
            return (T) new SearchByYearViewModel(mRepository, mGson);
        }
        if(modelClass == SearchByTopViewModel.class) {
            return (T) new SearchByTopViewModel(mRepository, mGson);
        }
        return (T) new ListAllViewModel(mRepository, mGson);
    }
}
