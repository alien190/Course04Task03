package com.example.alien.course04task03.ui.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.event.AuthErrorEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.NoSuchElementException;

import io.reactivex.Single;
import retrofit2.HttpException;

public abstract class BaseViewModel extends ViewModel {

    protected MutableLiveData<List<Repo>> mRepoList = new MutableLiveData<>();
    protected MutableLiveData<String> mErrorMessage = new MutableLiveData<>();
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
                                        throwableLocalDel -> handleRepoError(throwableLocalDel, repoFullName));
                            } else {
                                handleRepoError(throwable, repoFullName);
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

    @SuppressLint("CheckResult")
    public void updateFromRemoteRepository() {
        mRemoteRepository.getAll("")
                .doOnSubscribe(disposable -> mIsRefreshing.postValue(true))
                .flatMap(list -> mLocalRepository.insertItems(list, mUserLogin.getValue()))
                .doFinally(() -> mIsRefreshing.postValue(false))
                .subscribe((v)->{},this::handleAuthError );
    }

    abstract protected void updateFromLocalRepository();


    //todo сделать класс для централизованной обработки ошибок
    @SuppressLint("CheckResult")
    protected void handleRepoError(Throwable throwable, String repoFullName) {
        if ((throwable instanceof HttpException) && ((HttpException) throwable).code() == 404) {
            mLocalRepository.deleteItem(repoFullName).subscribe(
                    //todo перенести строку ошибки в ресурсы
                    ret -> mErrorMessage.postValue("Такой репозиторий не существует на сервере"),
                    this::handleAuthError
            );
        } else {
            handleAuthError(throwable);
        }
    }

    public MutableLiveData<Boolean> getIsRefreshing() {
        return mIsRefreshing;
    }


    protected void handleAuthError(Throwable throwable) {
        if (((HttpException) throwable).code() == 401) {
            EventBus.getDefault().postSticky(new AuthErrorEvent());
            //mIsAuthError.postValue(true);
        } else {
            handleCommonError(throwable);
        }
    }

    protected void handleCommonError(Throwable throwable) {
        mErrorMessage.postValue(throwable.getMessage());
    }

    public MutableLiveData<String> getErrorMessage() {
        return mErrorMessage;
    }
}
