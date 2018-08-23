package com.example.alien.course04task03.ui.filmList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.RepoSimple;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepoSimpleListViewHolder extends RecyclerView.ViewHolder {
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


    public RepoSimpleListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bind(RepoSimple repoSimple) {
        mTvName.setText(repoSimple.getName());
        //mTvDirector.setText(repoSimple.getDirector());
        //mTvYear.setText(String.valueOf(repoSimple.getYear()));
        //mTvRate.setText(StringUtils.rateToString(film.getRating()));
        mId = repoSimple.getId();
    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        view.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mId));
        view.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

}
