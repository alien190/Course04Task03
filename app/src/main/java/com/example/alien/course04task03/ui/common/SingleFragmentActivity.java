package com.example.alien.course04task03.ui.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.R;


public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_single_fragment);

        toothpickInject();

        if (savedInstanceState == null) {
            changeFragment(getFragment());
        }
    }

    protected abstract void toothpickInject();

    protected abstract Fragment getFragment();


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        toothpickCloseScope();
        super.onDestroy();
    }

    protected abstract void toothpickCloseScope();

    protected void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.single_fragment_container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }
}
