package com.example.alien.course04task03.repository.gitHubRepository;

import android.annotation.SuppressLint;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.data.model.ErrorsItem;
import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.RepoRequest;
import com.example.alien.course04task03.data.model.RepoResponse;
import com.example.alien.course04task03.data.model.RepoUpdate;
import com.example.alien.course04task03.data.model.Token;
import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.sharedPref.SharedPrefImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

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
        return null;
//        return mIGitHubApi.createRepo(repo).
//                map(repoResponse -> 1L)
//                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Long>> insertItems(List<Repo> repos) {
        return Single.just(new ArrayList<Long>());
    }

    @Override
    public Single<Repo> getItem(long id) {
        return null;
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<String> deleteItem(String repoFullName) {
        return mIGitHubApi.deleteRepo(repoFullName)
                .map(errorsItem -> {
                    if (errorsItem.getCode().startsWith("204")) {
                        return "";
                    } else {
                        return errorsItem.getMessage();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Repo>> getAll() {
        return mIGitHubApi.getRepos()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Repo>> search(String query) {
        return Single.just(new ArrayList<Repo>());
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
    public Single<Repo> createItem(String name, String description, String homePage) {
        RepoUpdate repoUpdate = new RepoUpdate(name, description, homePage);
        return mIGitHubApi.createRepo(repoUpdate)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Repo> updateItem(String repoFullName,Repo repoUpdate) {

            return mIGitHubApi.updateRepo(repoFullName, repoUpdate).subscribeOn(Schedulers.io());
    }

}
