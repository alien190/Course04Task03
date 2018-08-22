package com.example.oauth2.api;


import com.example.oauth2.BuildConfig;
import com.example.oauth2.model.Token;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAuthApi {

    @POST(BuildConfig.GET_TOKEN_URL)
    Single<Token> getToken(@Query("code") String code);

    @GET(BuildConfig.VALIDATE_TOKEN_URL)
    Single<Object> validateToken(@Query("access_token") String token);

}
