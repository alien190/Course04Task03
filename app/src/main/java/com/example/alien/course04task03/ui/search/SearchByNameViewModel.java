package com.example.alien.course04task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

public class SearchByNameViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByNameQuery = new MutableLiveData<>();

    public SearchByNameViewModel(IRepoRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    public MutableLiveData<String> getSearchByNameQuery() {
        return mSearchByNameQuery;
    }

    public void setSearchByNameQuery(CharSequence query) {
        this.mSearchByNameQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        mFilmList.postValue(mRepository.search(mSearchByNameQuery.getValue()));
    }
}
