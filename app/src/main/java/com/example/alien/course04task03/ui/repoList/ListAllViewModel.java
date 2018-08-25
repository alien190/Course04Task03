package com.example.alien.course04task03.ui.repoList;

import android.annotation.SuppressLint;

import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository, Single<User> user) {
        super(remoteRepository, localRepository, user);
        updateFromLocalRepository();
        updateFromRemoteRepository();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void updateFromLocalRepository() {
       // List<Repo> repos = mRemoteRepository.getAll();
        mLocalRepository.getAll(mUserLogin.getValue())
                .observeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mIsRefreshing.postValue(true))
                .doFinally(() -> mIsRefreshing.postValue(false))
                .subscribe(mRepoList::postValue);

    }
}
