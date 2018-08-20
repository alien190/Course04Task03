package com.example.alien.course04task03.api;


import com.example.alien.course04task03.model.Token;
import com.example.alien.course04task03.model.User;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAuthApi {

    @POST("login/oauth/access_token?accept=json")
    Single<Token> getToken(@Query("code") String code, @Query("client_id") String clientId, @Query("client_secret") String clientSecret);

}
