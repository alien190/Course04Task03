package com.example.alien.course04task03.ui.repoMain;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.alien.course04task03.BR;
import com.example.alien.course04task03.R;
import com.example.alien.course04task03.databinding.MainBindin;
import com.example.alien.course04task03.databinding.SearchByNameBinding;
import com.example.alien.course04task03.ui.common.BaseFragment;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.example.alien.course04task03.ui.event.AuthErrorEvent;
import com.example.alien.course04task03.ui.event.CloseActivityEvent;
import com.example.alien.course04task03.ui.launch.LaunchActivity;
import com.example.alien.course04task03.ui.repoDetail.RepoDetailDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;
import java.util.Scanner;

import javax.inject.Inject;

import timber.log.Timber;


public class RepoFragment extends BaseFragment {

    private static final String KEY_TYPE = "KeyType";

    @Inject
    protected BaseViewModel mViewModel;

    @Inject
    Context mContext;

    private int mSearchType;
    private ViewDataBinding mViewDataBinding;


    public static RepoFragment newInstance(String parentScopeName, int type) {

        Bundle args = new Bundle();
        args.putString(KEY_PARENT_SCOPE_NAME, parentScopeName);
        args.putInt(KEY_TYPE, type);

        RepoFragment fragment = new RepoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSearchType = getArguments().getInt(KEY_TYPE, 0);

        switch (mSearchType) {
            case RepoActivity.TYPE_SEARCH_BY_NAME: {
                mViewDataBinding = SearchByNameBinding.inflate(inflater, container, false);
                break;
            }
            default: {
                mViewDataBinding = MainBindin.inflate(inflater, container, false);
                setHasOptionsMenu(true);
                mSearchType = 0;
                break;
            }
        }

        return mViewDataBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewDataBinding.setVariable(BR.vm, mViewModel);
        mViewModel.getErrorMessage().observe(this, this::handleCommonError);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_add: {
                RepoDetailDialogFragment repoDetailDialogFragment = RepoDetailDialogFragment.newInstance(-1);
                repoDetailDialogFragment.show(getActivity().getSupportFragmentManager(), "repoDetailDialogFragment");
                return true;
            }

            case R.id.mi_search_by_name: {
                RepoActivity.startActivity(getContext(), RepoActivity.TYPE_SEARCH_BY_NAME);
                return true;
            }
            case R.id.mi_logout: {
                doLogout();
                return true;
            }

            case R.id.mi_exit: {
                doCloseActivity();
                return true;
            }
            default:
                return false;
        }
    }

    private void doLogout() {
        LaunchActivity.startActivity(mContext, true);
        doCloseActivity();
    }

    private void doCloseActivity() {
        EventBus.getDefault().post(new CloseActivityEvent());
    }

    private void generateData() {
        String json = "";

        try {
            AssetManager am = getContext().getAssets();
            InputStream is = am.open("filmList.json");
            try (Scanner s = new Scanner(is).useDelimiter("\\A")) {
                json = s.hasNext() ? s.next() : "";
            }
        } catch (Throwable t) {
            Timber.d(t);
        }
        mViewModel.generateData(json);
    }

    private void handleAuthError(Boolean isAuthError) {
        if (isAuthError != null && isAuthError) {
            new AlertDialog.Builder(mContext)
                    .setTitle(R.string.auth_error_title)
                    .setMessage(R.string.auth_error_msg)
                    .setPositiveButton(R.string.understand, (dialogInterface, i) -> {
                        LaunchActivity.startActivity(mContext, false);
                        doCloseActivity();
                    })
                    .create()
                    .show();
        }
    }

    private void handleCommonError(String errorMsg) {
        Toast.makeText(mContext, getString(R.string.error) + errorMsg, Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAuthError(AuthErrorEvent event) {
        handleAuthError(true);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
}

