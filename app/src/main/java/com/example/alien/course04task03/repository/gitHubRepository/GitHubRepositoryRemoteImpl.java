package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.RepoRequest;
import com.example.alien.course04task03.data.model.RepoResponse;
import com.example.alien.course04task03.data.model.Token;
import com.example.alien.course04task03.data.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GitHubRepositoryRemoteImpl implements IGitHubRepository {

    private IGitHubApi mIGitHubApi;

    public GitHubRepositoryRemoteImpl(IGitHubApi iGitHubApi) {
        mIGitHubApi = iGitHubApi;
    }

    @Override
    public Single<User> getUser(String token) {
        return mIGitHubApi.getUser(token)
                .subscribeOn(Schedulers.io());
    }

//    @Override
//    public Single<RepoResponse> createRepo(String token, RepoRequest repoRequest) {
//        return mIGitHubApi.createRepo( repoRequest)
//                .subscribeOn(Schedulers.io());
//    }

    @Override
    public Single<User> getUser() {
        return mIGitHubApi.getUser()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Long> insertItem(Repo repo) {
        return Single.just(0L);
    }

    @Override
    public Single<List<Long>> insertItems(List<Repo> repos) {
        return Single.just(new ArrayList<Long>());
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
        return mIGitHubApi.getRepos()
                .subscribeOn(Schedulers.io());
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
}
