package com.example.oauth2.repository.gitHubRepository;

import com.example.oauth2.api.IAuthApi;
import com.example.oauth2.model.Token;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GHRepositoryImpl implements IGHRepository {

    private IAuthApi mIAuthApi;

    public GHRepositoryImpl(IAuthApi iAuthApi) {
        mIAuthApi = iAuthApi;
    }

    @Override
    public Single<Boolean> validateToken(String token) {
        return mIAuthApi.validateToken(token)
                .subscribeOn(Schedulers.io())
                .map(user -> true);
    }

    @Override
    public Single<String> createToken(String code) {
        return mIAuthApi.getToken(code)
                .subscribeOn(Schedulers.io())
                .map(Token::getAccessToken);
    }
}
