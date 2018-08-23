package com.example.alien.course04task03.ui.filmList;

import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import java.util.List;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IGitHubRepository repository) {
        super(repository);
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        List<Repo> repos = mRepository.getAll();
        mRepoList.postValue(repos);
    }
}
