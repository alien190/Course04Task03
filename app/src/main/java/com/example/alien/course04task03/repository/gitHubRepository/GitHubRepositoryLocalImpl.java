package com.example.alien.course04task03.repository.gitHubRepository;

import android.support.v4.app.INotificationSideChannel;

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
                .subscribeOn(Schedulers.io())
                .map(itemId -> {
                    EventBus.getDefault().post(new OnRepoDataBaseUpdate());
                    return itemId;
                });
    }

    @Override
    public Single<List<Long>> insertItems(List<Repo> repos) {
        return Single.fromCallable(() -> mIGitHubDao.insertItems(repos))
                .subscribeOn(Schedulers.io())
                .map(list -> {
                    EventBus.getDefault().post(new OnRepoDataBaseUpdate());
                    return list;
                });
    }

    @Override
    public Single<Repo> getItem(long id) {
        return mIGitHubDao.getItem(id).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<String> deleteItem(String repoFullName) {
        return Single.fromCallable(() -> mIGitHubDao.deleteItem(repoFullName))
                .map(count -> {
                    EventBus.getDefault().post(new OnRepoDataBaseUpdate());
                    return String.valueOf(count);
                })
                .subscribeOn(Schedulers.io());
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
    public Single<Long> createRepoAndSave(String name, String description, String homePage) {
        return null;
    }

    @Override
    public Single<Repo> updateItem(String repoFullName, String name, String description, String homePage) {
        return null;
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
