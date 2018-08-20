package com.example.alien.course04task03.ui.token;

import android.arch.lifecycle.MutableLiveData;

public interface ITokenViewModel {
    int STATE_SPLASH = 1;
    int STATE_AUTH = 2;
    int STATE_SHOW_AUTH = 3;
    int STATE_COMPLETE = 4;

    MutableLiveData<Integer> getState();
    void createToken(String code, String clientId, String clientSecret);
    void showAuthorizationForm();
}
