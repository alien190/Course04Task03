package com.example.alien.course04task03.di.mainActivity;

import android.app.Application;
import android.arch.persistence.room.Room;


import com.example.alien.course04task03.data.IRepoDao;
import com.example.alien.course04task03.data.RepoDataBase;

import javax.inject.Inject;
import javax.inject.Provider;

class RepoDaoProvider implements Provider<IRepoDao> {
    private Application mApplication;

    @Inject
    public RepoDaoProvider(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Override
    public IRepoDao get() {
        RepoDataBase database = Room.databaseBuilder(mApplication.getApplicationContext(), RepoDataBase.class, "RepoDatabase").build();
        return database.getRepoDao();
    }
}
