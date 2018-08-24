package com.example.alien.course04task03.ui.repoList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.Repo;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepoListViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private long mId;

    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvHomePage)
    TextView mTvHomePage;
    @BindView(R.id.tvDescription)
    TextView mTvDescription;
    @BindView(R.id.tvSize)
    TextView mTvSize;
    @BindView(R.id.tvWatches)
    TextView mTvWatches;

    private String mRepoFullName;


    public RepoListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bind(Repo repo) {
        mTvName.setText(repo.getName());
        mTvDescription.setText(repo.getDescription());
        mTvHomePage.setText(repo.getHomePage());
        mTvSize.setText(String.valueOf(repo.getSize()));
        mTvWatches.setText(String.valueOf(repo.getWatchersCount()));
        mId = repo.getId();
        mRepoFullName = repo.getFullName();
    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        view.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mRepoFullName));
        view.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

}
