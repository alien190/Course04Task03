package com.example.alien.course04task03.ui.repoList;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.Repo;


public class RepoListAdapter extends ListAdapter<Repo, RepoListViewHolder> {

    private IOnItemClickListener mOnItemClickListener;

    private static DiffUtil.ItemCallback<Repo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo Repo, @NonNull Repo t1) {
            return Repo.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo Repo, @NonNull Repo t1) {
            return Repo.equals(t1);
        }
    };


    public RepoListAdapter(IOnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RepoListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.li_repo_simple_list, viewGroup, false);
        return new RepoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListViewHolder RepoListViewHolder, int i) {
        RepoListViewHolder.bind(getItem(i));
        RepoListViewHolder.setOnItemClickListener(mOnItemClickListener);
    }

}
