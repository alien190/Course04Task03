package com.example.alien.course04task03.di.mainActivity;

import android.app.Application;
import android.arch.persistence.room.Room;


import com.example.alien.course04task03.data.GitHubDataBase;
import com.example.alien.course04task03.data.IGitHubDao;

import javax.inject.Inject;
import javax.inject.Provider;

class RepoDaoProvider implements Provider<IGitHubDao> {
    private Application mApplication;

    @Inject
    public RepoDaoProvider(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Override
    public IGitHubDao get() {
        GitHubDataBase database = Room.databaseBuilder(mApplication.getApplicationContext(), GitHubDataBase.class, "RepoDatabase").build();
        return database.getRepoDao();
    }
}
