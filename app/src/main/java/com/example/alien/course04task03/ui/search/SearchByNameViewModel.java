package com.example.alien.course04task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

public class SearchByNameViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByNameQuery = new MutableLiveData<>();

    public SearchByNameViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository) {
        super(remoteRepository, localRepository);
        updateFromLocalRepository();
        updateFromRemoteRepository();
    }

    public MutableLiveData<String> getSearchByNameQuery() {
        return mSearchByNameQuery;
    }

    public void setSearchByNameQuery(CharSequence query) {
        this.mSearchByNameQuery.setValue(query.toString());
        updateFromLocalRepository();
    }

    @Override
    protected void updateFromLocalRepository() {
        mRepoList.postValue(mRemoteRepository.search(mSearchByNameQuery.getValue()));
    }
}
