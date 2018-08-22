package com.example.alien.course04task03.ui.main;

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


import com.example.alien.course04task03.R;
import com.example.alien.course04task03.databinding.MainBindin;
import com.example.alien.course04task03.databinding.SearchByDirectorBinding;
import com.example.alien.course04task03.databinding.SearchByNameBinding;
import com.example.alien.course04task03.databinding.SearchByTopBinding;
import com.example.alien.course04task03.databinding.SearchByYearBinding;
import com.example.alien.course04task03.ui.common.BaseFragment;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.example.alien.course04task03.ui.filmDetail.FilmDetailDialogFragment;

import java.io.InputStream;
import java.util.Scanner;

import javax.inject.Inject;

import timber.log.Timber;


public class MainFragment extends BaseFragment {

    private static final String KEY_TYPE = "KeyType";

    @Inject
    protected BaseViewModel mViewModel;

    private int mSearchType;
    private ViewDataBinding mViewDataBinding;


    public static MainFragment newInstance(String parentScopeName, int type) {

        Bundle args = new Bundle();
        args.putString(KEY_PARENT_SCOPE_NAME, parentScopeName);
        args.putInt(KEY_TYPE, type);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSearchType = getArguments().getInt(KEY_TYPE, 0);

        switch (mSearchType) {
            case MainActivity.TYPE_SEARCH_BY_DIRECTOR: {
                mViewDataBinding = SearchByDirectorBinding.inflate(inflater, container, false);
                break;
            }
            case MainActivity.TYPE_SEARCH_BY_NAME: {
                mViewDataBinding = SearchByNameBinding.inflate(inflater, container, false);
                break;
            }
            case MainActivity.TYPE_SEARCH_BY_YEAR: {
                mViewDataBinding = SearchByYearBinding.inflate(inflater, container, false);
                break;
            }
            case MainActivity.TYPE_SEARCH_BY_TOP: {
                mViewDataBinding = SearchByTopBinding.inflate(inflater, container, false);
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
        //mViewDataBinding.setVariable(BR.vm, mViewModel);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_generate: {
                generateData();
                return true;
            }
            case R.id.mi_add: {
                FilmDetailDialogFragment filmDetailDialogFragment = FilmDetailDialogFragment.newInstance(-1);
                filmDetailDialogFragment.show(getActivity().getSupportFragmentManager(), "filmDetailDialogFragment");
                return true;
            }

            case R.id.mi_search_by_name: {
                MainActivity.startActivity(getContext(), MainActivity.TYPE_SEARCH_BY_NAME);
                return true;
            }
            case R.id.mi_search_by_year: {
                MainActivity.startActivity(getContext(), MainActivity.TYPE_SEARCH_BY_YEAR);
                return true;
            }
            case R.id.mi_search_by_director: {
                MainActivity.startActivity(getContext(), MainActivity.TYPE_SEARCH_BY_DIRECTOR);
                return true;
            }
            case R.id.mi_search_by_top: {
                MainActivity.startActivity(getContext(), MainActivity.TYPE_SEARCH_BY_TOP);
                return true;
            }
            default:
                return false;
        }
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
}
