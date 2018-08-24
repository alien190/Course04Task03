package com.example.alien.course04task03.di.mainActivity;


import android.support.v4.app.Fragment;

import com.example.alien.course04task03.ui.repoList.RepoListAdapter;
import com.example.alien.course04task03.ui.repoList.IOnItemClickListener;

import toothpick.config.Module;

public class RepoListFragmentModule extends Module {
    private Fragment mFragment;

    public RepoListFragmentModule(Fragment fragment) {
        mFragment = fragment;

        if (mFragment instanceof IOnItemClickListener) {
            bind(RepoListAdapter.class).toInstance(new RepoListAdapter((IOnItemClickListener) mFragment));
        } else {
            bind(RepoListAdapter.class).toInstance(new RepoListAdapter(null));
        }
    }


}
