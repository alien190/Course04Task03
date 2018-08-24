package com.example.alien.course04task03.di.commonActivity;

import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.ui.repoList.ListAllFragment;
import com.example.alien.course04task03.ui.repoMain.RepoFragment;

import toothpick.config.Module;

public class CommonActivityModule extends Module {

    private AppCompatActivity mActivity;
    private String mScopeName;
    private int mType;

    public CommonActivityModule(AppCompatActivity activity, String scopeName, int type) {
        mActivity = activity;
        mScopeName = scopeName;
        mType = type;

        bind(RepoFragment.class).toInstance(RepoFragment.newInstance(mScopeName, mType));
        bind(ListAllFragment.class).toInstance(ListAllFragment.newInstance(mScopeName));
        bind(AppCompatActivity.class).toInstance(mActivity);
    }
}