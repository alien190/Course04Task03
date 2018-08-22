package com.example.oauth2.repository.gitHubRepository;

import com.example.alien.course04task03.api.IAuthApi;
import com.example.alien.course04task03.api.IAuthInterceptor;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.model.RepoRequest;
import com.example.alien.course04task03.model.RepoResponse;
import com.example.alien.course04task03.model.Token;
import com.example.alien.course04task03.model.User;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GHRepositoryImpl implements IGHRepository {

    private IGitHubApi mIGitHubApi;
    private IAuthApi mIAuthApi;
    private IAuthInterceptor mIAuthInterceptor;

    public GHRepositoryImpl(IGitHubApi iGitHubApi, IAuthApi iAuthApi, IAuthInterceptor iAuthInterceptor) {
        mIGitHubApi = iGitHubApi;
        mIAuthApi = iAuthApi;
        mIAuthInterceptor = iAuthInterceptor;
    }

    @Override
    public Single<Boolean> validateToken(String token) {
        return mIGitHubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .map(user -> true);
    }

    @Override
    public Single<String> createToken(String code, String clientId, String clientSecret) {
        return mIAuthApi.getToken(code, clientId, clientSecret)
                .subscribeOn(Schedulers.io())
                .map(Token::getAccessToken);
    }


}
