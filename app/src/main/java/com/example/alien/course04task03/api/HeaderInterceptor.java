package com.example.alien.course04task03.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor, IAuthInterceptor {

    private String mToken = null;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader("Accept", "application/json");
        if (mToken != null) {
            builder.addHeader("Authorization", "mToken " + mToken);
        }

        return chain.proceed(builder.build());
    }

    public void setToken(String token) {
        this.mToken = token;
    }
}
