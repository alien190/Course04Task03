package com.example.alien.course04task03.api;


import io.reactivex.Single;

public interface IGitHubApi {
    Single<User> getUser(String token);

}
