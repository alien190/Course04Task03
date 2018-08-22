package com.example.alien.course04task03.di.application;

import android.content.Context;

import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.alien.course04task03.repository.sharedPref.SharedPrefImpl;
import com.example.oauth2.repository.tokenStorage.ITokenStorage;

import javax.inject.Inject;
import javax.inject.Provider;

class SharedPrefProvider implements Provider<SharedPrefImpl> {

    private SharedPrefImpl mSharedPref;

    @Inject
    public SharedPrefProvider(Context context) {
        mSharedPref = new SharedPrefImpl(context);
    }

    @Override
    public SharedPrefImpl get() {
        return mSharedPref;
    }
}
