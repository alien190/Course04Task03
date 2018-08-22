package com.example.oauth2.token;

import android.arch.lifecycle.MutableLiveData;

public interface ITokenViewModel {
    int STATE_SPLASH = 1;
    int STATE_AUTH = 2;
    int STATE_SHOW_AUTH = 3;
    int STATE_COMPLETE = 4;
    int STATE_AUTH_INTERACTIVE = 5;

    MutableLiveData<Integer> getState();

    MutableLiveData<String> getToken();

    void createToken(String code);

    void showAuthorizationForm();

    void startNewAuth(String token);
}
