package com.example.alien.course04task03.ui.filmList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.Film;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FilmListViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private long mId;

    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvDirector)
    TextView mTvDirector;
    @BindView(R.id.tvYear)
    TextView mTvYear;
    @BindView(R.id.tvRate)
    TextView mTvRate;


    public FilmListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bind(Film film) {
        mTvName.setText(film.getName());
        mTvDirector.setText(film.getDirector());
        mTvYear.setText(String.valueOf(film.getYear()));
        //mTvRate.setText(StringUtils.rateToString(film.getRating()));
        mId = film.getId();
    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        view.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mId));
        view.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

}
