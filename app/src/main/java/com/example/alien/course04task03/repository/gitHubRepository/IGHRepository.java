package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.data.model.RepoRequest;
import com.example.alien.course04task03.data.model.RepoResponse;
import com.example.alien.course04task03.data.model.User;

import io.reactivex.Single;

public interface IGHRepository {
    Single<User> getUser(String token);
    Single<User> getUser();
    Single<RepoResponse> createRepo(String token, RepoRequest repoRequest);
}
