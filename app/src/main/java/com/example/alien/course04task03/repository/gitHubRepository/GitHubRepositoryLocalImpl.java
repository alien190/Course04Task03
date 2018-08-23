package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.data.IGitHubDao;
import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;

import java.util.List;

import io.reactivex.Single;

public class GitHubRepositoryLocalImpl implements IGitHubRepository {

    private IGitHubDao mIGitHubDao;

    public GitHubRepositoryLocalImpl(IGitHubDao repoDao) {
        this.mIGitHubDao = repoDao;
    }

    @Override
    public long insertItem(Repo repo) {
        return 0;
    }

    @Override
    public void insertItems(List<Repo> repos) {

    }

    @Override
    public Repo getItem(long id) {
        return null;
    }

    @Override
    public boolean deleteItem(long id) {
        return false;
    }

    @Override
    public List<Repo> getAll() {
        return null;
    }

    @Override
    public List<Repo> search(String query) {
        return null;
    }

    @Override
    public List<Repo> searchInBounds(int startYear, int endYear) {
        return null;
    }

    @Override
    public List<Repo> searchByDirector(String name) {
        return null;
    }

    @Override
    public List<Repo> getTopRepoSimples(int count) {
        return null;
    }

    @Override
    public long createRepoAndSave(String name, String director, int year, double rating) {
        return 0;
    }

    @Override
    public void createRepoAndUpdate(long id, String name, String director, int year, double rating) {

    }

    @Override
    public Single<User> getUser(String token) {
        return null;
    }

    @Override
    public Single<User> getUser() {
        return null;
    }
}
