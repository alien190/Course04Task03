package com.example.alien.course04task03.repository.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oauth2.repository.tokenStorage.ITokenStorage;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SharedPrefImpl implements ISharedPref, ITokenStorage {

    private static final String TOKEN_PREF = "TokenPref";
    private static final String TOKEN_KEY = "TokenKey2";
    private SharedPreferences mSharedPreferences;

    public SharedPrefImpl(Context context) {
        mSharedPreferences = context.getSharedPreferences(TOKEN_PREF, Context.MODE_PRIVATE);
    }

    @Override
    public Single<String> readToken() {
        return Single.fromCallable(() -> mSharedPreferences.getString(TOKEN_KEY, ""))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Boolean> writeToken(String token) {
        return Single.fromCallable(() -> {
            mSharedPreferences.edit()
                    .putString(TOKEN_KEY, token)
                    .apply();
            return true;
        });
    }
}
