package com.example.alien.course04task03.ui.gHR;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.course04task03.R;

public class GhrFragment extends Fragment {
    public static GhrFragment newInstance() {

        Bundle args = new Bundle();

        GhrFragment fragment = new GhrFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_git_hub_repository, container, false);
    }
}
