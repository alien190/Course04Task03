package com.example.oauth2.repository.tokenValidator;


import android.arch.lifecycle.MutableLiveData;


public interface ITokenValidator {

    int TOKEN_IN_PROGRESS = 1;
    int TOKEN_EMPTY = 2;
    int TOKEN_VALID = 3;
    int TOKEN_INVALID = 4;
    int TOKEN_VALIDATION_ERROR = 5;
    int TOKEN_CREATED = 6;
    int TOKEN_CREATION_ERROR = 7;

    String getToken();

    MutableLiveData<Integer> getTokenState();

    void validateToken(String token);

    void createToken(String code);

    void obtainToken();
}
