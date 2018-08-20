package com.example.alien.course04task03.repository.gitHubRepository;

import io.reactivex.Single;

public interface IGHRepository {
    Single<Boolean> validateToken(String token);
    Single<String> createToken(String code, String clientId, String clientSecret);
}
