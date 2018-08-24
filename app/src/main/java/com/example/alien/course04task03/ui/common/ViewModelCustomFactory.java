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
    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;

    public ViewModelCustomFactory(IGitHubRepository remoteRepository, IGitHubRepository localRepository) {
        mRemoteRepository = remoteRepository;
        mLocalRepository = localRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == SearchByNameViewModel.class) {
            return (T) new SearchByNameViewModel(mRemoteRepository, mLocalRepository);
        }
        if(modelClass == SearchByDirectorViewModel.class) {
            return (T) new SearchByDirectorViewModel(mRemoteRepository, mLocalRepository);
        }
        if(modelClass == SearchByYearViewModel.class) {
            return (T) new SearchByYearViewModel(mRemoteRepository, mLocalRepository);
        }
        if(modelClass == SearchByTopViewModel.class) {
            return (T) new SearchByTopViewModel(mRemoteRepository, mLocalRepository);
        }
        return (T) new ListAllViewModel(mRemoteRepository, mLocalRepository);
    }
}
