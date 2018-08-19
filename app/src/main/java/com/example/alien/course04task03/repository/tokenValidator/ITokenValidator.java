package com.example.alien.course04task03.repository.tokenValidator;


import android.arch.lifecycle.MutableLiveData;


public interface ITokenValidator {

    int TOKEN_IN_PROGRESS = 0;
    int TOKEN_EMPTY = 1;
    int TOKEN_VALID = 2;
    int TOKEN_INVALID = 3;
    int TOKEN_VALIDATION_ERROR = 4;

    String getToken();
    MutableLiveData<Integer> getTokenState();

    void validateToken(String token);
}
