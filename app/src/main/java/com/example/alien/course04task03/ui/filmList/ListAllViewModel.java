package com.example.alien.course04task03.ui.filmList;

import android.annotation.SuppressLint;

import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IGitHubRepository repository) {
        super(repository);
        updateFromRepository();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void updateFromRepository() {
       // List<Repo> repos = mRepository.getAll();
        mRepository.getAll().observeOn(Schedulers.io())
                .subscribe(mRepoList::postValue);

    }
}
