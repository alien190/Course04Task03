package com.example.alien.course04task03.ui.token;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.alien.course04task03.di.tokenActivity.TokenActivityModule;
import com.example.alien.course04task03.ui.common.SingleFragmentActivity;
import com.example.alien.course04task03.ui.gHR.GhrActivity;

import javax.inject.Inject;

import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;

public class TokenActivity extends SingleFragmentActivity {

    @Inject
    protected ITokenViewModel mViewModel;

    @Inject
    protected TokenFragment mTokenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.getState().observe(this, this::changeUiState);
    }


    @Override
    protected Fragment getFragment() {
        return mTokenFragment;
    }

    @Override
    protected void toothpickInject() {
        Scope scope = Toothpick.openScopes("Application", this.getClass().getSimpleName());
        scope.installModules(new TokenActivityModule(this));
        Toothpick.inject(this, scope);
        Toothpick.inject(mTokenFragment, scope);
    }

    @Override
    protected void toothpickCloseScope() {
        Toothpick.closeScope(this.getClass().getSimpleName());
    }


    private void changeUiState(int viewModelSate) {
        if (viewModelSate == ITokenViewModel.STATE_COMPLETE) {
            Intent intent = new Intent(this, GhrActivity.class);
            startActivity(intent);
            finish();
        }
        Timber.d("ViewModel state change: %d", viewModelSate);
    }
}



