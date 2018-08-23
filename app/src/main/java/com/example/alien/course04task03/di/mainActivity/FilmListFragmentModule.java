package com.example.alien.course04task03.di.mainActivity;


import android.support.v4.app.Fragment;

import com.example.alien.course04task03.ui.filmList.RepoSimpleListAdapter;
import com.example.alien.course04task03.ui.filmList.IOnItemClickListener;

import toothpick.config.Module;

public class FilmListFragmentModule extends Module {
    private Fragment mFragment;

    public FilmListFragmentModule(Fragment fragment) {
        mFragment = fragment;

        if (mFragment instanceof IOnItemClickListener) {
            bind(RepoSimpleListAdapter.class).toInstance(new RepoSimpleListAdapter((IOnItemClickListener) mFragment));
        } else {
            bind(RepoSimpleListAdapter.class).toInstance(new RepoSimpleListAdapter(null));
        }
    }


}
