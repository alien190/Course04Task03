package com.example.alien.course04task03.repository.gitHubRepository;



import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;

import java.util.List;

import io.reactivex.Single;

public interface IGitHubRepository {

    Single<Repo> insertItem(Repo repo);

    Single<List<Long>> insertItems(List<Repo> repos, String userLogin);

    Single<Repo> getItem(long id);

    Single<String> deleteItem(String repoFullName);

    Single<List<Repo>> getAll(String userLogin);

    Single<List<Repo>> search(String query);

    List<Repo> searchInBounds(int startYear, int endYear);

    List<Repo> searchByDirector(String name);

    List<Repo> getTopRepoSimples(int count);

   // Single<Repo> createItem(String name, String description, String homePage);

    Single<Repo> updateItem(String repoFullName,Repo repoUpdate);

    Single<User> getUser(String token);

    Single<User> getUser();

    interface IOnRepoDataBaseUpdate {
    }

}
