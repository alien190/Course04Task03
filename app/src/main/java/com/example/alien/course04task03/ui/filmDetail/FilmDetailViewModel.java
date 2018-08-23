package com.example.alien.course04task03.ui.filmDetail;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import timber.log.Timber;

public class FilmDetailViewModel extends BaseViewModel {
    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mYear = new MutableLiveData<>();
    private MutableLiveData<String> mDirector = new MutableLiveData<>();
    private MutableLiveData<String> mRating = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsSaved = new MutableLiveData<>();
    private Long mRepoId;
    private int mTitleId;

    public FilmDetailViewModel(IGitHubRepository repository, Long repoId) {
        super(repository);
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
//        Film film = mRepository.getItem(mRepoId);
//        mName.postValue(film.getName());
//        mDirector.postValue(film.getDirector());
//        mYear.postValue(String.valueOf(film.getYear()));
//        mRating.postValue(String.valueOf(film.getRating()));
    }

    public MutableLiveData<String> getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName.postValue(mName);
    }

    public MutableLiveData<String> getYear() {
        return mYear;
    }


    public MutableLiveData<String> getDirector() {
        return mDirector;
    }

    public MutableLiveData<String> getRating() {
        return mRating;
    }


    public void apply(String name, String director, String year, String rating) {
        int yearInt = 0;
        double ratingDbl = 0;
        try {
            yearInt = Integer.valueOf(year);
            ratingDbl = Double.valueOf(rating);
        } catch (Throwable t) {
            Timber.d(t);
        }
        if (mRepoId < 0) {
          //  mRepository.createFilmAndSave(name, director, yearInt, ratingDbl);
        } else {
          //  mRepository.createFilmAndUpdate(mRepoId, name, director, yearInt, ratingDbl);
        }
        mIsSaved.postValue(true);
    }

    public MutableLiveData<Boolean> getIsSaved() {
        return mIsSaved;
    }

    public int getTitleId() {
        return mTitleId;
    }

    @Override
    protected void updateFromRepository() {

    }
}
