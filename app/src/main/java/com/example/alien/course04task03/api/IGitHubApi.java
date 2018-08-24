package com.example.alien.course04task03.api;


import com.example.alien.course04task03.data.model.ErrorsItem;
import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.RepoRequest;
import com.example.alien.course04task03.data.model.RepoResponse;
import com.example.alien.course04task03.data.model.RepoUpdate;
import com.example.alien.course04task03.data.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGitHubApi {
    @GET("user")
    Single<User> getUser(@Query("access_token") String token);

    @GET("user")
    Single<User> getUser();

    @POST("/user/repos")
    Single<Repo> createRepo(@Body RepoUpdate repoUpdate);

    @GET("/user/repos")
    Single<List<Repo>> getRepos();

    @PATCH("/repos/{repoFullName}")
    Single<Repo> updateRepo(@Path(value = "repoFullName", encoded = true) String repoFullName, @Body Repo repoUpdate);

    @DELETE("/repos/{repoFullName}")
    Single<ErrorsItem> deleteRepo(@Path(value = "repoFullName", encoded = true) String repoFullName);

}
