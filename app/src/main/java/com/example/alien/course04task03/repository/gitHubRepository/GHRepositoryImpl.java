package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.data.model.RepoRequest;
import com.example.alien.course04task03.data.model.RepoResponse;
import com.example.alien.course04task03.data.model.Token;
import com.example.alien.course04task03.data.model.User;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GHRepositoryImpl implements IGHRepository {

    private IGitHubApi mIGitHubApi;

    public GHRepositoryImpl(IGitHubApi iGitHubApi) {
        mIGitHubApi = iGitHubApi;
    }

    @Override
    public Single<User> getUser(String token) {
        return mIGitHubApi.getUser(token)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<RepoResponse> createRepo(String token, RepoRequest repoRequest) {
        return mIGitHubApi.createRepo( repoRequest)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<User> getUser() {
        return mIGitHubApi.getUser()
                .subscribeOn(Schedulers.io());
    }
}
