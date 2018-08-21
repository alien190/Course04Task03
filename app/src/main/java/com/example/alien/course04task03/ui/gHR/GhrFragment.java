package com.example.alien.course04task03.ui.gHR;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.di.ghractivity.GhrActivityModule;
import com.example.alien.course04task03.model.Token;
import com.example.alien.course04task03.ui.token.TokenActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import toothpick.Scope;
import toothpick.Toothpick;

public class GhrFragment extends Fragment implements IGhrViewModel.createRepositoryCallBack {

    @Inject
    protected IGhrViewModel mGhrViewModel;


    @BindView(R.id.etNameNewRepo)
    EditText etName;

    @BindView(R.id.etDescriptionNewRepo)
    EditText etDescription;

    @BindView(R.id.etHomePageNewRepo)
    EditText etHomePage;


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
        toothpickInject();
        return view;
    }

    @Override
    public void onSuccessRepoCreation() {
        showToast(R.string.success_repo_creation);
    }

    @Override
    public void onCommonErrorRepoCreation() {
        showToast(R.string.common_error_repo_creation);
    }

    @Override
    public void onAuthErrorRepoCreation() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.auth_error_title)
                .setMessage(R.string.auth_error_msg)
                .setPositiveButton(R.string.understand, (dialogInterface, i) -> startTokenActivity()).show();
    }

    private void showToast(int msgId) {
        Toast.makeText(getContext(), getText(msgId), Toast.LENGTH_SHORT).show();
    }

    private void startTokenActivity() {
        TokenActivity.startActivity(getContext());
        getActivity().finish();
    }

    @OnClick(R.id.btCreate)
    protected void createRepository() {
        if(validateInput()) {
            mGhrViewModel.createRepository(etName.getText().toString(),
                    etDescription.getText().toString(),
                    etHomePage.getText().toString(),
                    this);
        }
    }
    boolean validateInput(){
        if(etName.getText().toString().isEmpty()) {
            etName.setError(getText(R.string.et_empty_error));
            return false;
        } else {
            return true;
        }
    }

    protected void toothpickInject() {
        Scope scope = Toothpick.openScopes("Application", "GitHubRepository");
        Toothpick.inject(this, scope);
    }
}
