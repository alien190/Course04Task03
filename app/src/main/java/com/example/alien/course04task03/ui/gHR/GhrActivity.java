package com.example.alien.course04task03.ui.gHR;

import android.support.v4.app.Fragment;

import com.example.alien.course04task03.di.ghractivity.GhrActivityModule;
import com.example.alien.course04task03.ui.common.SingleFragmentActivity;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class GhrActivity extends SingleFragmentActivity {
    @Inject
    GhrFragment mGhrFragment;

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
}
