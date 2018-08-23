package com.example.alien.course04task03.ui.token;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.alien.course04task03.di.tokenActivity.TokenActivityModule;
import com.example.alien.course04task03.ui.common.SingleFragmentActivity;
import com.example.alien.course04task03.ui.main.MainActivity;
import com.example.oauth2.token.TokenFragment;

import javax.inject.Inject;

import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;

public class TokenActivity extends SingleFragmentActivity {

    private static final String START_NEW_AUTH_KEY = "StartNewAuthKey";

    @Inject
    protected TokenFragment mTokenFragment;

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
        Scope scope = Toothpick.openScopes("Application", getClass().getSimpleName());
        scope.installModules(new TokenActivityModule(getClass().getSimpleName()));
        Toothpick.inject(this, scope);
    }

    @Override
    protected void toothpickCloseScope() {
       // Toothpick.closeScope(getClass().getSimpleName());
    }


    private void tokenObserver(String token) {
        if (token!=null && !token.isEmpty()) {
            MainActivity.startActivity(this, MainActivity.TYPE_LIST);
            finish();
        }
        Timber.d("tokenObserver.token: %s", token);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TokenActivity.class);
        intent.putExtra(START_NEW_AUTH_KEY, true);
        context.startActivity(intent);
    }
}



