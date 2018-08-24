package com.example.alien.course04task03.ui.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public abstract class BaseViewModel extends ViewModel {

    protected MutableLiveData<List<Repo>> mRepoList = new MutableLiveData<>();
    protected MutableLiveData<String> mResultMessage = new MutableLiveData<>();
    //  protected OrderedRealmCollection<Repo> data;
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsRefreshing = new MutableLiveData<>();

    protected final IGitHubRepository mRemoteRepository;
    protected final IGitHubRepository mLocalRepository;

    public BaseViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;

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
                .subscribe(msg -> {
                }, this::errorHandler);
    }

//    public OrderedRealmCollection<Repo> getData() {
//        return data;
//    }

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
        mRemoteRepository.getAll()
                .doOnSubscribe(disposable -> mIsRefreshing.postValue(true))
                .flatMap(mLocalRepository::insertItems)
                .doFinally(() -> mIsRefreshing.postValue(false))
                .subscribe();
    }

    abstract protected void updateFromLocalRepository();

    protected void errorHandler(Throwable throwable) {
        mResultMessage.postValue(throwable.getMessage());
    }

    public MutableLiveData<Boolean> getIsRefreshing() {
        return mIsRefreshing;
    }
}
