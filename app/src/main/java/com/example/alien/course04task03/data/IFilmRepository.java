package com.example.alien.course04task03.data;

import com.example.alien.course04task03.data.model.Film;

import java.util.List;

public interface IFilmRepository {

    long insertItem(Film film);

    void insertItems(List<Film> films);

    Film getItem(long id);

    boolean deleteItem(long id);

    List<Film> getAll();

    void updateItem(Film film);

    List<Film> search(String query);

    List<Film> searchInBounds(int startYear, int endYear);

    List<Film> searchByDirector(String name);

    List<Film> getTopFilms(int count);

    long createFilmAndSave(String name, String director, int year, double rating);

    void createFilmAndUpdate(long id, String name, String director, int year, double rating);

    interface IOnFilmDataBaseUpdate {}

}
