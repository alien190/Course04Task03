package com.example.alien.course04task03.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.alien.course04task03.data.model.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;

@Dao
public interface IGitHubDao {
    @Query("SELECT * FROM repo WHERE repo.login = :userLogin")
    Single<List<Repo>> getAll(String userLogin);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(Repo repo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertItems(List<Repo> repos);

    @Query("SELECT * from repo where repo.name like :name")
    Single<List<Repo>> searchByName(String name);

    @Query("SELECT * from repo where repo.id = :itemId")
    Single<Repo> getItem(Long itemId);

    @Query("DELETE from repo where repo.fullName = :repoFullName")
    int deleteItem(String repoFullName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateItem(Repo repo);

    @Query("SELECT id FROM repo WHERE repo.fullName = :fullName")
    int getIdByRepoFullName(String fullName);
}
