package com.example.oauth2.repository.tokenStorage;

import io.reactivex.Single;

public interface ITokenStorage {
    Single<String> readToken();
    Single<Boolean> writeToken(String token);
}
