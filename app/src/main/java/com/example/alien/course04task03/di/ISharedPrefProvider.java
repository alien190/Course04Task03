package com.example.alien.course04task03.di;

import android.content.Context;

import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.alien.course04task03.repository.sharedPref.SharedPrefImpl;

import javax.inject.Inject;
import javax.inject.Provider;

class ISharedPrefProvider implements Provider<ISharedPref> {
    private Context mContext;

    @Inject
    public ISharedPrefProvider(Context context) {
        mContext = context;
    }

    @Override
    public ISharedPref get() {
        return new SharedPrefImpl(mContext);
    }
}
