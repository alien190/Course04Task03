package com.example.alien.course04task03.di.gitHubRepository;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;


import com.example.alien.course04task03.data.GitHubDataBase;
import com.example.alien.course04task03.data.IGitHubDao;

import javax.inject.Inject;
import javax.inject.Provider;

class GitHubDaoProvider implements Provider<IGitHubDao> {
    private Context mContext;

    @Inject
    public GitHubDaoProvider(Context context) {
        this.mContext = context;
    }

    @Override
    public IGitHubDao get() {
        GitHubDataBase database = Room.databaseBuilder(mContext, GitHubDataBase.class, "RepoDatabase").build();
        return database.getRepoDao();
    }
}
