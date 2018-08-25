package com.example.alien.course04task03.ui.repoDetail;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.Repo;
import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RepoDetailViewModel extends BaseViewModel {
    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mDescription = new MutableLiveData<>();
    private MutableLiveData<String> mHomePage = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsSaved = new MutableLiveData<>();
    private Long mRepoId;
    private int mTitleId;
    private Disposable mDisposable;
    private String mRepoFullName;

    public RepoDetailViewModel(IGitHubRepository remoteRepository, IGitHubRepository localRepository, Long repoId, Single<User> user) {
        super(remoteRepository, localRepository, user);
        mIsSaved.postValue(false);
        mRepoId = repoId;
        if (mRepoId >= 0) {
            loadFilm();
            mTitleId = R.string.dialog_title_edit_film;
        } else {
            mTitleId = R.string.dialog_title_new_film;
        }
    }

    private void loadFilm() {
        mDisposable = mLocalRepository.getItem(mRepoId)
                .subscribe(repo -> {
                    mName.postValue(repo.getName());
                    mDescription.postValue(repo.getDescription());
                    mHomePage.postValue(repo.getHomePage());
                    mRepoFullName = repo.getFullName();
                });
    }

    public MutableLiveData<String> getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName.postValue(mName);
    }

    public MutableLiveData<String> getDescription() {
        return mDescription;
    }


    public MutableLiveData<String> getHomePage() {
        return mHomePage;
    }


    @SuppressLint("CheckResult")
    public void apply(String name, String description, String homePage) {

        Repo repoUpdate = new Repo(name, description, homePage);

        if (mRepoId < 0) {
            mRemoteRepository.insertItem(repoUpdate)
                    .flatMap(mLocalRepository::insertItem)
                    .subscribe();
        } else {

            mRemoteRepository.updateItem(mRepoFullName, repoUpdate)
                    .flatMap(repo -> mLocalRepository.updateItem(mRepoFullName, repo))
                    .subscribe(repo -> {
                    }, this::userErrorHandler);
        }
//mIsSaved.postValue(true)
    }

    public MutableLiveData<Boolean> getIsSaved() {
        return mIsSaved;
    }

    public int getTitleId() {
        return mTitleId;
    }

    @Override
    protected void updateFromLocalRepository() {

    }

    @Override
    protected void onCleared() {
        //  mDisposable.dispose();
        super.onCleared();
    }
}
