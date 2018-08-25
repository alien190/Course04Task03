package com.example.alien.course04task03.ui.repoList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    @BindView(R.id.rlDetail)
    RelativeLayout rlDetail;
    @BindView(R.id.ivDetail)
    ImageView ivDetail;
    @BindView(R.id.rlName)
    RelativeLayout rlName;
    @BindView(R.id.rlDescription)
    RelativeLayout rlDescription;
    @BindView(R.id.tvLabelDescription)
    TextView tvLabelDescription;

    private String mRepoFullName;


    public RepoListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
        rlName.setOnClickListener(view -> showDetail(!(rlDetail.getVisibility() == View.VISIBLE)));
    }

    public void bind(Repo repo) {
        mTvName.setText(repo.getName());
        String description = repo.getDescription();
        if (description==null || description.isEmpty()) {
            mTvDescription.setText(R.string.no_description);
        } else {
            mTvDescription.setText(description);
        }

        mTvHomePage.setText(repo.getHomePage());
        mTvSize.setText(String.valueOf(repo.getSize()));
        mTvWatches.setText(String.valueOf(repo.getWatchersCount()));
        mId = repo.getId();
        mRepoFullName = repo.getFullName();
        showDetail(false);

    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        rlDescription.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mRepoFullName));
        rlDescription.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
        rlDetail.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mRepoFullName));
        rlDetail.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

    private void showDetail(boolean isVisible) {
        if (isVisible) {
            rlDetail.setVisibility(View.VISIBLE);
            ivDetail.setImageResource(R.drawable.ic_expand_less_black_24dp);
            tvLabelDescription.setVisibility(View.VISIBLE);
        } else {
            rlDetail.setVisibility(View.GONE);
            ivDetail.setImageResource(R.drawable.ic_expand_more_black_24dp);
            tvLabelDescription.setVisibility(View.GONE);
        }
    }

}
