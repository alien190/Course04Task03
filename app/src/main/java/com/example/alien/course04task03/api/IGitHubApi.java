package com.example.alien.course04task03.api;


import com.example.alien.course04task03.model.NewRepoRequest;
import com.example.alien.course04task03.model.NewRepoResponse;
import com.example.alien.course04task03.model.Token;
import com.example.alien.course04task03.model.User;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGitHubApi {

    @GET("user")
    Single<User> getUser(@Query("access_token") String token);

    @POST("/user/repos")
    Single<NewRepoResponse> createRepo(@Query("access_token") String token, NewRepoRequest newRepoRequest);
}
