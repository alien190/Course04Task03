package com.example.alien.course04task03.api;


import io.reactivex.Single;
import com.example.alien.course04task03.model.User;

public interface IGitHubApi {
    Single<User> getUser(String token);

}
