package com.example.alien.course04task03.ui.repoList;

import android.annotation.SuppressLint;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

import io.reactivex.schedulers.Schedulers;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository) {
        super(remoteRepository, localRepository);
        updateFromRepository();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void updateFromRepository() {
       // List<Repo> repos = mRemoteRepository.getAll();
        mRemoteRepository.getAll().observeOn(Schedulers.io())
                .subscribe(mRepoList::postValue);

    }
}
