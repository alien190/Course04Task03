package com.example.alien.course04task03.ui.filmDetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.data.IRepoRepository;
import com.google.gson.Gson;

public class FilmDetailViewModelCustomFactory implements ViewModelProvider.Factory {
    private IRepoRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    public FilmDetailViewModelCustomFactory(IRepoRepository repository, Gson gson, Long filmId) {

        this.mRepository = repository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FilmDetailViewModel(mRepository, mGson, mFilmId);

    }
}
