package com.example.alien.course04task03.ui.gHR;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alien.course04task03.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GhrFragment extends Fragment {

    @Inject
    protected IGhrViewModel mGhrViewModel;


    @BindView(R.id.btCreate)
    Button btnCreate;


    public static GhrFragment newInstance() {

        Bundle args = new Bundle();

        GhrFragment fragment = new GhrFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_git_hub_repository, container, false);
        ButterKnife.bind(this, view);

        btnCreate = view.findViewById(R.id.btCreate);
        btnCreate.setOnClickListener(v -> mGhrViewModel.createRepository());

        return view;
    }
}
