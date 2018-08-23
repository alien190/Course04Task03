package com.example.alien.course04task03.api;

import android.annotation.SuppressLint;

import com.example.oauth2.repository.tokenStorage.ITokenStorage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private String mToken = null;

    @SuppressLint("CheckResult")
    public HeaderInterceptor(ITokenStorage tokenStorage) {
        if (tokenStorage != null) {
            tokenStorage.readToken().subscribe(this::setToken);
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Accept", "application/json");
        if (mToken != null) {
            builder.addHeader("Authorization", "token " + mToken);
        }
        return chain.proceed(builder.build());
    }

    private void setToken(String token) {
        mToken = token;
    }
}
