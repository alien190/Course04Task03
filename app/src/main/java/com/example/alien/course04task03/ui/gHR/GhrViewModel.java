package com.example.alien.course04task03.ui.gHR;

import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;

public class GhrViewModel extends ViewModel implements IGhrViewModel {
    private IGHRepository mIGHRepository;

    public GhrViewModel(IGHRepository iGitHubApi) {
        mIGHRepository = iGitHubApi;
    }
}
