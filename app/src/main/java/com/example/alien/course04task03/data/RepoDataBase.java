package com.example.alien.course04task03.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.alien.course04task03.data.model.RepoSimple;

@Database(entities = {RepoSimple.class}, version = 1)
public abstract class RepoDataBase extends RoomDatabase {
    public abstract IRepoDao getRepoDao();
}