package com.example.alien.course04task03.ui.repoDetail;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.di.mainActivity.RepoDetailDialogFragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;

public class RepoDetailDialogFragment extends DialogFragment {

    private Scope mScope;
    private static final String KEY_REPO_ID = "RepoDetailDialogFragment.KeyFilmId";

    @Inject
    protected RepoDetailViewModel mViewModel;

    @BindView(R.id.tvTitle)
    protected TextView tvTitle;

    @BindView(R.id.etName)
    protected EditText etName;

    @BindView(R.id.etHomePage)
    protected EditText etHomePage;

    @BindView(R.id.etDescription)
    protected EditText etDescription;

   private DialogInterface.OnClickListener mOnClickListener = (dialogInterface, i) -> {
        mViewModel.apply(etName.getText().toString(),
                etDescription.getText().toString(),
                etHomePage.getText().toString());

    };

    public static RepoDetailDialogFragment newInstance(long id) {

        Bundle args = new Bundle();
        args.putLong(KEY_REPO_ID, id);
        RepoDetailDialogFragment fragment = new RepoDetailDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        toothpickInject();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fr_detail_dialog_fragment, null);
        initUI(view);

        builder.setView(view)
                .setPositiveButton(R.string.btn_save_label, mOnClickListener)
                .setNegativeButton(R.string.btn_cancel_label, null);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void toothpickInject() {
        long id = -1;
        if (getArguments() != null) {
            id = getArguments().getLong(KEY_REPO_ID, -1);
        }

        mScope = Toothpick.openScopes("Application", this.getClass().getSimpleName());
        mScope.installModules(new RepoDetailDialogFragmentModule(this, id));
        Toothpick.inject(this, mScope);
    }


    @Override
    public void onDetach() {
        Toothpick.closeScope(this.getClass().getSimpleName());
        super.onDetach();
    }

    private void initUI(View view) {
        ButterKnife.bind(this, view);

        tvTitle.setText(mViewModel.getTitleId());
        mViewModel.getName().observe(this, str -> etName.setText(str));
        mViewModel.getHomePage().observe(this, str -> etHomePage.setText(str));
        mViewModel.getDescription().observe(this, str -> etDescription.setText(str));
    }


}
