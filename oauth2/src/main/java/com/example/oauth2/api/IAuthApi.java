package com.example.oauth2.api;


import com.example.oauth2.BuildConfig;
import com.example.oauth2.model.Token;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAuthApi {

    @POST(BuildConfig.AUTH_PATH)
    Single<Token> getToken(@Query("code") String code);

    @POST("/user")
    Single<Object> validateToken(@Query("token") String token);

}
