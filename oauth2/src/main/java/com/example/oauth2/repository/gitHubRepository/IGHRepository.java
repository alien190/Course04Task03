package com.example.oauth2.repository.gitHubRepository;


import io.reactivex.Single;

public interface IGHRepository {
    Single<Boolean> validateToken(String token);
    Single<String> createToken(String code);
}
