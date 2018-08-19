package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.api.IGitHubApi;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GHRepositoryImpl implements IGHRepository {

    private IGitHubApi mIGitHubApi;

    public GHRepositoryImpl(IGitHubApi IGitHubApi) {
        mIGitHubApi = IGitHubApi;
    }

    @Override
    public Single<Boolean> validateToken(String token) {
        return mIGitHubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(user -> true);
    }
}
