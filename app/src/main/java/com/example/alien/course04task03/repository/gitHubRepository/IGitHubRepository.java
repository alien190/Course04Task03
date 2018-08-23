package com.example.alien.course04task03.repository.gitHubRepository;


import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;

import java.util.List;

import io.reactivex.Single;

public interface IGitHubRepository {

    long insertItem(Repo repo);

    void insertItems(List<Repo> repos);

    Repo getItem(long id);

    boolean deleteItem(long id);

    List<Repo> getAll();

    List<Repo> search(String query);

    List<Repo> searchInBounds(int startYear, int endYear);

    List<Repo> searchByDirector(String name);

    List<Repo> getTopRepoSimples(int count);

    long createRepoAndSave(String name, String director, int year, double rating);

    void createRepoAndUpdate(long id, String name, String director, int year, double rating);

    Single<User> getUser(String token);

    Single<User> getUser();

    interface IOnRepoDataBaseUpdate {
    }

}
