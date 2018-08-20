package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.api.IAuthApi;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.model.Token;
import com.example.alien.course04task03.model.User;

import javax.inject.Scope;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GHRepositoryImpl implements IGHRepository {

    private IGitHubApi mIGitHubApi;
    private IAuthApi mIAuthApi;

    public GHRepositoryImpl(IGitHubApi iGitHubApi, IAuthApi iAuthApi) {
        mIGitHubApi = iGitHubApi;
        mIAuthApi = iAuthApi;
    }

    @Override
    public Single<Boolean> validateToken(String token) {
        return mIGitHubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(user -> true);
    }

    @Override
    public Single<String> createToken(String code, String clientId, String clientSecret) {
        return mIAuthApi.getToken(code, clientId, clientSecret)
                .subscribeOn(Schedulers.io())
                .map(Token::getAccessToken);
    }

    @Override
    public Single<User> getUser(String token) {
        return mIGitHubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
