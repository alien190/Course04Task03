package com.example.alien.course04task03.ui.filmList;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.RepoSimple;


public class RepoSimpleListAdapter extends ListAdapter<RepoSimple, RepoSimpleListViewHolder> {

    private IOnItemClickListener mOnItemClickListener;

    private static DiffUtil.ItemCallback<RepoSimple> DIFF_CALLBACK = new DiffUtil.ItemCallback<RepoSimple>() {
        @Override
        public boolean areItemsTheSame(@NonNull RepoSimple RepoSimple, @NonNull RepoSimple t1) {
            return RepoSimple.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull RepoSimple RepoSimple, @NonNull RepoSimple t1) {
            return RepoSimple.equals(t1);
        }
    };


    public RepoSimpleListAdapter(IOnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RepoSimpleListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.li_repo_simple_list, viewGroup, false);
        return new RepoSimpleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoSimpleListViewHolder RepoSimpleListViewHolder, int i) {
        RepoSimpleListViewHolder.bind(getItem(i));
        RepoSimpleListViewHolder.setOnItemClickListener(mOnItemClickListener);
    }

}
