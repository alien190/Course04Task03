package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.ui.filmDetail.FilmDetailViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class FilmDetailViewModelCustomFactoryProvider implements Provider<FilmDetailViewModelCustomFactory> {

    protected IRepoRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    @Inject
    public FilmDetailViewModelCustomFactoryProvider(IRepoRepository mRepository, Gson gson, @Named("FilmId") Long filmId) {
        this.mRepository = mRepository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @Override
    public FilmDetailViewModelCustomFactory get() {
        return new FilmDetailViewModelCustomFactory(mRepository, mGson, mFilmId);
    }
}
