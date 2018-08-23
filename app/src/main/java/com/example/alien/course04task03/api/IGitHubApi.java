package com.example.alien.course04task03.api;


import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.RepoRequest;
import com.example.alien.course04task03.data.model.RepoResponse;
import com.example.alien.course04task03.data.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGitHubApi {
    @GET("user")
    Single<User> getUser(@Query("access_token") String token);

    @GET("user")
    Single<User> getUser();

    @POST("/user/repos")
    Single<RepoResponse> createRepo(@Body RepoRequest repoRequest);

    @GET("/user/repos")
    Single<List<Repo>> getRepos();
}
