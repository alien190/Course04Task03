package com.example.alien.course04task03.ui.token;

import android.arch.lifecycle.MutableLiveData;

public interface ITokenViewModel {
    int STATE_SPLASH = 1;
    int STATE_AUTH = 2;
    int STATE_COMPLETE = 3;

    MutableLiveData<Integer> getState();
}
