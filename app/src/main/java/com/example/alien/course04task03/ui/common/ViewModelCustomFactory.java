package com.example.alien.course04task03.ui.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.repoList.ListAllViewModel;
import com.example.alien.course04task03.ui.search.SearchByNameViewModel;

import io.reactivex.Single;

public class ViewModelCustomFactory implements ViewModelProvider.Factory {
    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;
    private Single<User> mUser;

    public ViewModelCustomFactory(IGitHubRepository remoteRepository, IGitHubRepository localRepository, Single<User> user) {
        mRemoteRepository = remoteRepository;
        mLocalRepository = localRepository;
        mUser = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == SearchByNameViewModel.class) {
            return (T) new SearchByNameViewModel(mRemoteRepository, mLocalRepository, mUser);
        }
        return (T) new ListAllViewModel(mRemoteRepository, mLocalRepository, mUser);
    }
}
