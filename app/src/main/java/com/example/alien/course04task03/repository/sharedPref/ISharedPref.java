package com.example.alien.course04task03.repository.sharedPref;

import io.reactivex.Single;

public interface ISharedPref {
    Single<String> readToken();
    void writeToken(String token);
}
