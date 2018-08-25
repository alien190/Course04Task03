package com.example.alien.course04task03.ui.launch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.alien.course04task03.di.tokenActivity.TokenActivityModule;
import com.example.alien.course04task03.ui.common.SingleFragmentActivity;
import com.example.alien.course04task03.ui.repoMain.RepoActivity;
import com.example.oauth2.ui.TokenFragment;

import javax.inject.Inject;

import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;

public class LaunchActivity extends SingleFragmentActivity {

    private static final String START_NEW_AUTH_KEY = "LaunchActivity.StartNewAuthKey";
    private static final String DO_LOGOUT_KEY = "LaunchActivity.DoLogoutKey";

    @Inject
    protected TokenFragment mTokenFragment;

    private Boolean mDoLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTokenFragment.getToken().observe(this, this::tokenObserver);
    }

    @Override
    protected Fragment getFragment() {
        return mTokenFragment;
    }

    @Override
    protected void toothpickInject() {
        mDoLogout = getIntent().getBooleanExtra(DO_LOGOUT_KEY, false);

        Scope scope = Toothpick.openScopes("Application", getClass().getSimpleName());
        scope.installModules(new TokenActivityModule(getClass().getSimpleName(), mDoLogout));
        Toothpick.inject(this, scope);
    }

    @Override
    protected void toothpickCloseScope() {
        // Toothpick.closeScope(getClass().getSimpleName());
    }


    private void tokenObserver(String token) {
        if (token != null && !token.isEmpty()) {
            RepoActivity.startActivity(this, RepoActivity.TYPE_LIST);
            finish();
        }
        Timber.d("tokenObserver.token: %s", token);
    }

    public static void startActivity(Context context, Boolean doLogout) {
        Intent intent = new Intent(context, LaunchActivity.class);
        intent.putExtra(START_NEW_AUTH_KEY, true);
        intent.putExtra(DO_LOGOUT_KEY, doLogout);
        context.startActivity(intent);
    }
}



