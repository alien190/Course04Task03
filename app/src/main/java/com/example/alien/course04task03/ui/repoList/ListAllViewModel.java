package com.example.alien.course04task03.ui.repoList;

import android.annotation.SuppressLint;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository) {
        super(remoteRepository, localRepository);
        updateFromLocalRepository();
        updateFromRemoteRepository();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void updateFromLocalRepository() {
       // List<Repo> repos = mRemoteRepository.getAll();
        mLocalRepository.getAll()
                .observeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mIsRefreshing.postValue(true))
                .doFinally(() -> mIsRefreshing.postValue(false))
                .subscribe(mRepoList::postValue);

    }
}
