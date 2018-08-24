package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.data.IGitHubDao;
import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class GitHubRepositoryLocalImpl implements IGitHubRepository {

    private IGitHubDao mIGitHubDao;

    public GitHubRepositoryLocalImpl(IGitHubDao repoDao) {
        this.mIGitHubDao = repoDao;
    }

    @Override
    public Single<Long> insertItem(Repo repo) {
        return Single.fromCallable(() -> mIGitHubDao.insertItem(repo))
                .observeOn(Schedulers.io())
                .map(itemId -> {
                    EventBus.getDefault().post(new OnRepoDataBaseUpdate());
                    return itemId;
                });
    }

    @Override
    public Single<List<Long>> insertItems(List<Repo> repos) {
        return Single.fromCallable(() -> mIGitHubDao.insertItems(repos))
                .observeOn(Schedulers.io())
                .map(list -> {
                    EventBus.getDefault().post(new OnRepoDataBaseUpdate());
                    return list;
                });
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
    public Single<List<Repo>> getAll() {
        return mIGitHubDao.getAll().subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Repo>> search(String query) {
        if (query == null || query.length() < 3) {
            return Single.just(new ArrayList<Repo>());
        } else {
            query = "%" + query + "%";
            return mIGitHubDao.searchByName(query).subscribeOn(Schedulers.io());
        }
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

    private class OnRepoDataBaseUpdate implements IOnRepoDataBaseUpdate {
    }
}
