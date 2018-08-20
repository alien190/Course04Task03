package com.example.alien.course04task03.ui.gHR;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class GhrViewModel extends ViewModel implements IGhrViewModel {
    private IGHRepository mIGHRepository;
    private ISharedPref mSharedPref;
    private MutableLiveData<Boolean> mIsTokenValid = new MutableLiveData<>();
    private MutableLiveData<String> mUserName = new MutableLiveData<>();
    private MutableLiveData<String> mToken = new MutableLiveData<>();
    private Disposable mDisposable;

    public GhrViewModel(IGHRepository ighRepository, ISharedPref sharedPref) {
        mIGHRepository = ighRepository;
        mSharedPref = sharedPref;

        mDisposable = sharedPref.readToken()
                .flatMap((Function<String, Single<User>>) token -> {
                    mToken.postValue(token);
                    return mIGHRepository.getUser(token);
                })
                .subscribe(user -> {
                            mUserName.postValue(user.getLogin());
                            mIsTokenValid.postValue(true);
                        },
                        throwable -> {
                            mIsTokenValid.postValue(false);
                            Timber.e(throwable);
                        });
    }

    @Override
    protected void onCleared() {
        mDisposable.dispose();
        super.onCleared();
    }

    public MutableLiveData<String> getUserName() {
        return mUserName;
    }
}
