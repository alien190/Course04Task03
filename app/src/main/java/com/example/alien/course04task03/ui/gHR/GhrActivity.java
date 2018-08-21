package com.example.alien.course04task03.ui.gHR;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.alien.course04task03.di.ghractivity.GhrActivityModule;
import com.example.alien.course04task03.ui.common.SingleFragmentActivity;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class GhrActivity extends SingleFragmentActivity {
    @Inject
    GhrFragment mGhrFragment;

    @Inject
    IGhrViewModel mViewModel;

    @Override
    protected void toothpickInject() {
        Scope scope = Toothpick.openScopes("Application", this.getClass().getSimpleName());
        scope.installModules(new GhrActivityModule(this));
        Toothpick.inject(this, scope);
        Toothpick.inject(mGhrFragment, scope);
    }

    @Override
    protected Fragment getFragment() {
        return mGhrFragment;
    }

    @Override
    protected void toothpickCloseScope() {
        Toothpick.closeScope(this.getClass().getSimpleName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.getUserName().observe(this, this::setTitle);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GhrActivity.class);
        context.startActivity(intent);
    }
}
