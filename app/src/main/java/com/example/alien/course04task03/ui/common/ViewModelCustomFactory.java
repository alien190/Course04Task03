package com.example.alien.course04task03.ui.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.repoList.ListAllViewModel;
import com.example.alien.course04task03.ui.search.SearchByDirectorViewModel;
import com.example.alien.course04task03.ui.search.SearchByNameViewModel;
import com.example.alien.course04task03.ui.search.SearchByTopViewModel;
import com.example.alien.course04task03.ui.search.SearchByYearViewModel;

public class ViewModelCustomFactory implements ViewModelProvider.Factory {
    private IGitHubRepository mRepository;

    public ViewModelCustomFactory(IGitHubRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == SearchByNameViewModel.class) {
            return (T) new SearchByNameViewModel(mRepository);
        }
        if(modelClass == SearchByDirectorViewModel.class) {
            return (T) new SearchByDirectorViewModel(mRepository);
        }
        if(modelClass == SearchByYearViewModel.class) {
            return (T) new SearchByYearViewModel(mRepository);
        }
        if(modelClass == SearchByTopViewModel.class) {
            return (T) new SearchByTopViewModel(mRepository);
        }
        return (T) new ListAllViewModel(mRepository);
    }
}
