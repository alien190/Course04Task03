package com.example.alien.course04task03.data;


import com.example.alien.course04task03.data.model.RepoSimple;

import java.util.List;

public interface IRepoRepository {

    long insertItem(RepoSimple RepoSimple);

    void insertItems(List<RepoSimple> RepoSimples);

    RepoSimple getItem(long id);

    boolean deleteItem(long id);

    List<RepoSimple> getAll();

    void updateItem(RepoSimple RepoSimple);

    List<RepoSimple> search(String query);

    List<RepoSimple> searchInBounds(int startYear, int endYear);

    List<RepoSimple> searchByDirector(String name);

    List<RepoSimple> getTopRepoSimples(int count);

    long createRepoSimpleAndSave(String name, String director, int year, double rating);

    void createRepoSimpleAndUpdate(long id, String name, String director, int year, double rating);

    interface IOnRepoSimpleDataBaseUpdate {}

}
