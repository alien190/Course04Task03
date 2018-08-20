package com.example.alien.course04task03.ui.gHR;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;
import com.example.alien.course04task03.ui.token.TokenViewModel;

import javax.inject.Inject;

public class GhrViewModelFactory implements ViewModelProvider.Factory{
    private IGHRepository mIGHRepository;

    @Inject
    public GhrViewModelFactory(IGHRepository ighRepository) {
        this.mIGHRepository = ighRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GhrViewModel(mIGHRepository);
    }
}
