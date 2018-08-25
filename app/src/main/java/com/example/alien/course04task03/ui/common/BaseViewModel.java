package com.example.alien.course04task03.ui.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.NoSuchElementException;

import io.reactivex.Single;
import retrofit2.HttpException;
import timber.log.Timber;

public abstract class BaseViewModel extends ViewModel {

    protected MutableLiveData<List<Repo>> mRepoList = new MutableLiveData<>();
    protected MutableLiveData<String> mResultMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsRefreshing = new MutableLiveData<>();

    protected final IGitHubRepository mRemoteRepository;
    protected final IGitHubRepository mLocalRepository;
    protected MutableLiveData<String> mUserLogin = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public BaseViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository, Single<User> user) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;

        user.subscribe(retUser -> mUserLogin.postValue(retUser.getLogin()), this::userErrorHandler);
        EventBus.getDefault().register(this);

        mRepoList.observeForever(list ->
        {
            mIsEmpty.postValue(list != null && list.isEmpty());

//            if (list instanceof RealmResults) {
//                RealmResults<Repo> RepoSimpleRealmResults = (RealmResults<Repo>) list;
//                RepoSimpleRealmResults.addChangeListener(RepoSimples -> mIsEmpty.postValue(RepoSimples.isEmpty()));
//            }
        });

//        updateFromLocalRepository();
//        updateFromRemoteRepository();
    }

    protected void userErrorHandler(Throwable throwable) {

    }

    public MutableLiveData<List<Repo>> getRepoList() {
        return mRepoList;
    }

    public MutableLiveData<Boolean> getIsEmpty() {
        return mIsEmpty;
    }

    public void generateData(String json) {
//        Type type = new TypeToken<List<Repo>>() {
//        }.getType();
//        List<Repo> repos = mGson.fromJson(json, type);
//        mRemoteRepository.insertItems(repos);
    }

    @SuppressLint("CheckResult")
    public void deleteItem(String repoFullName) {
        mRemoteRepository.deleteItem(repoFullName)
                .subscribe((msg) -> {
                        },
                        throwable -> {
                            if (throwable instanceof NoSuchElementException) {
                                mLocalRepository.deleteItem(repoFullName).subscribe(
                                        ret -> {
                                        },
                                        throwableLocalDel -> repoErrorHandler(throwableLocalDel, repoFullName));
                            } else {
                                repoErrorHandler(throwable, repoFullName);
                            }
                        });
    }


    @Override
    protected void onCleared() {
        EventBus.getDefault().unregister(this);
        super.onCleared();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRepoSimpleDataBaseUpdate(IGitHubRepository.IOnRepoDataBaseUpdate event) {
        updateFromLocalRepository();
    }

    public void updateFromRemoteRepository() {
        mRemoteRepository.getAll("")
                .doOnSubscribe(disposable -> mIsRefreshing.postValue(true))
                .flatMap(mLocalRepository::insertItems)
                .doFinally(() -> mIsRefreshing.postValue(false))
                .subscribe();
    }

    abstract protected void updateFromLocalRepository();

    @SuppressLint("CheckResult")
    protected void repoErrorHandler(Throwable throwable, String repoFullName) {
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 404) {
                mLocalRepository.deleteItem(repoFullName).subscribe(
                        ret -> mResultMessage.postValue("Такой репозиторий не существует на сервере"),
                        throwable1 -> mResultMessage.postValue(throwable.getMessage())
                );
            }
            if (((HttpException) throwable).code() == 401) {
//todo реализовать реакция на http eror 401
            }
        } else {
            mResultMessage.postValue(throwable.getMessage());
        }
    }

    public MutableLiveData<Boolean> getIsRefreshing() {
        return mIsRefreshing;
    }
}
