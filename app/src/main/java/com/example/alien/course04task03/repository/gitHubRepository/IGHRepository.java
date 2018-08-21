package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.model.RepoRequest;
import com.example.alien.course04task03.model.RepoResponse;
import com.example.alien.course04task03.model.User;

import io.reactivex.Single;

public interface IGHRepository {
    Single<Boolean> validateToken(String token);
    Single<String> createToken(String code, String clientId, String clientSecret);
    Single<User> getUser(String token);
    Single<RepoResponse> createRepo(String token, RepoRequest repoRequest);
    void setAuthHeaderToken(String token);
}
