package com.example.alien.course04task03.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.example.alien.course04task03.data.model.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;

@Dao
public abstract class IGitHubDao {
    @Query("SELECT * FROM repo WHERE repo.login = :userLogin")
    public abstract Single<List<Repo>> getAll(String userLogin);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertItem(Repo repo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insertItems(List<Repo> repos);

    @Query("SELECT * from repo where repo.name like :name")
    public abstract Single<List<Repo>> searchByName(String name);

    @Query("SELECT * from repo where repo.id = :itemId")
    public abstract Single<Repo> getItem(Long itemId);

    @Query("DELETE from repo where repo.fullName = :repoFullName")
    public abstract int deleteItem(String repoFullName);

    @Query("DELETE from repo where repo.login = :userLogin")
    public abstract int deleteItems(String userLogin);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract int updateItem(Repo repo);

    @Query("SELECT id FROM repo WHERE repo.fullName = :fullName")
    public abstract int getIdByRepoFullName(String fullName);


    //todo зделать более интелектуальное обновление БД с учетом удаленных элементов на сервере
    @Transaction
    public List<Long> updateRepos(List<Repo> repos, String userLogin) {
        deleteItems(userLogin);
        return insertItems(repos);
    }
}
