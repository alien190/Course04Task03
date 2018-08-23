package com.example.alien.course04task03.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.alien.course04task03.data.model.Repo;

@Database(entities = {Repo.class}, version = 1)
public abstract class GitHubDataBase extends RoomDatabase {
    public abstract IGitHubDao getRepoDao();
}