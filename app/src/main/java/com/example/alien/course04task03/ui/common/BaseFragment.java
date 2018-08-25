package com.example.alien.course04task03.ui.common;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.di.mainActivity.RepoListFragmentModule;
import com.example.alien.course04task03.ui.repoMain.RepoActivity;

import toothpick.Scope;
import toothpick.Toothpick;

public abstract class BaseFragment extends Fragment {

    public static final String KEY_PARENT_SCOPE_NAME = "KeyParentScopeName";

    private String mScopeName;
    private String mParentScopeName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParentScopeName = ((RepoActivity)context).getScopeName();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mParentScopeName = getArguments().getString(KEY_PARENT_SCOPE_NAME);

        mScopeName = mParentScopeName + "." + this.getClass().getSimpleName();
        Scope scope = Toothpick.openScopes(mParentScopeName, mScopeName);
        scope.installModules(new RepoListFragmentModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    public void onDestroy() {
        Toothpick.closeScope(mScopeName);
        super.onDestroy();
    }

    public void setScopeName(String scopeName) {
        this.mScopeName = scopeName;
    }

//    public void onAuthErrorRepoCreation() {
//        new AlertDialog.Builder(getContext())
//                .setTitle(R.string.auth_error_title)
//                .setMessage(R.string.auth_error_msg)
//                .setPositiveButton(R.string.understand, (dialogInterface, i) -> startTokenActivity()).show();
//    }
}
