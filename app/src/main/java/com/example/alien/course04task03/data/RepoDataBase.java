package com.example.alien.course04task03.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {RepoSimple.class}, version = 1)
public abstract class RepoDataBase extends RoomDatabase {
    public abstract IRepoDao getRepoDao();
}