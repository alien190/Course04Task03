package com.example.alien.course04task03.ui.filmList;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.Film;


public class FilmListAdapter extends ListAdapter<Film, FilmListViewHolder> {

    private IOnItemClickListener mOnItemClickListener;

    private static DiffUtil.ItemCallback<Film> DIFF_CALLBACK = new DiffUtil.ItemCallback<Film>() {
        @Override
        public boolean areItemsTheSame(@NonNull Film film, @NonNull Film t1) {
            return film.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Film film, @NonNull Film t1) {
            return film.equals(t1);
        }
    };


    public FilmListAdapter(IOnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FilmListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.li_film_list, viewGroup, false);
        return new FilmListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListViewHolder filmListViewHolder, int i) {
        filmListViewHolder.bind(getItem(i));
        filmListViewHolder.setOnItemClickListener(mOnItemClickListener);
    }

}
