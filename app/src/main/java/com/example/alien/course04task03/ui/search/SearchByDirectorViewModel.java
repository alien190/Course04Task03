package com.example.alien.course04task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

public class SearchByDirectorViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByDirectorQuery = new MutableLiveData<>();

    public SearchByDirectorViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository) {
        super(remoteRepository, localRepository);
        updateFromLocalRepository();
        updateFromRemoteRepository();
    }


    public MutableLiveData<String> getSearchByDirectorQuery() {
        return mSearchByDirectorQuery;
    }

    public void setSearchByDirectorQuery(CharSequence query) {
        this.mSearchByDirectorQuery.setValue(query.toString());
        updateFromLocalRepository();
    }

    @Override
    protected void updateFromLocalRepository() {
        mRepoList.postValue(mRemoteRepository.searchByDirector(mSearchByDirectorQuery.getValue()));
    }
}
