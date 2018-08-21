package com.example.alien.course04task03.ui.gHR;

import android.arch.lifecycle.MutableLiveData;

public interface IGhrViewModel {
    MutableLiveData<String> getUserName();
    void createRepository(String name, String description, String homePage, createRepositoryCallBack callBack);

    interface createRepositoryCallBack{
        void onSuccessRepoCreation();
        void onCommonErrorRepoCreation();
        void onAuthErrorRepoCreation();
    }
}
