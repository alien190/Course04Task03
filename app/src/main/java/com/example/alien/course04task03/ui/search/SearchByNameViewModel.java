package com.example.alien.course04task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class SearchByNameViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByNameQuery = new MutableLiveData<>();
    private Disposable mDisposable;

    public SearchByNameViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository, Single<User> user) {
        super(remoteRepository, localRepository, user);
        updateFromLocalRepository();
        updateFromRemoteRepository();
    }

    public MutableLiveData<String> getSearchByNameQuery() {
        return mSearchByNameQuery;
    }

    public void doSearchByNameQuery(CharSequence query) {
        this.mSearchByNameQuery.setValue(query.toString());
        updateFromLocalRepository();
    }

    @Override
    protected void updateFromLocalRepository() {
        mDisposable = mLocalRepository.search(mSearchByNameQuery.getValue())
                .subscribe(list -> mRepoList.postValue(list));
    }

    @Override
    protected void onCleared() {
        mDisposable.dispose();
        super.onCleared();
    }
}
