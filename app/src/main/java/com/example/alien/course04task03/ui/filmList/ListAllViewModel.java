package com.example.alien.course04task03.ui.filmList;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.data.model.RepoSimple;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import java.util.List;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IRepoRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        List<RepoSimple> repos = mRepository.getAll();
        mRepoList.postValue(repos);
    }
}
