package com.example.alien.course04task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

public class SearchByDirectorViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByDirectorQuery = new MutableLiveData<>();

    public SearchByDirectorViewModel(IRepoRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }


    public MutableLiveData<String> getSearchByDirectorQuery() {
        return mSearchByDirectorQuery;
    }

    public void setSearchByDirectorQuery(CharSequence query) {
        this.mSearchByDirectorQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        mRepoList.postValue(mRepository.searchByDirector(mSearchByDirectorQuery.getValue()));
    }
}
