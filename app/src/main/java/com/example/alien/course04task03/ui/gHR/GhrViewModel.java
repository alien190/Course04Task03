package com.example.alien.course04task03.ui.gHR;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.model.RepoRequest;
import com.example.alien.course04task03.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import retrofit2.HttpException;
import timber.log.Timber;

public class GhrViewModel extends ViewModel implements IGhrViewModel {
    private IGHRepository mIGHRepository;
    private ISharedPref mSharedPref;
    private MutableLiveData<Boolean> mIsTokenValid = new MutableLiveData<>();
    private MutableLiveData<String> mUserName = new MutableLiveData<>();
    private MutableLiveData<String> mToken = new MutableLiveData<>();
    private CompositeDisposable mDisposable = new CompositeDisposable();

    public GhrViewModel(IGHRepository ighRepository, ISharedPref sharedPref) {
        mIGHRepository = ighRepository;
        mSharedPref = sharedPref;

        mDisposable.add(mSharedPref.readToken()
                .flatMap((Function<String, Single<User>>) token -> {
                    mToken.postValue(token);
                    mIGHRepository.setAuthHeaderToken(token);
                    return mIGHRepository.getUser();
                })
                .subscribe(user -> {
                            mUserName.postValue(user.getLogin());
                            mIsTokenValid.postValue(true);
                        },
                        throwable -> {
                            mIsTokenValid.postValue(false);
                            Timber.e(throwable);
                        }));
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
        super.onCleared();
    }

    public MutableLiveData<String> getUserName() {
        return mUserName;
    }

    @Override
    public void createRepository(String name, String description, String homePage, createRepositoryCallBack callBack) {
        RepoRequest repoRequest = new RepoRequest();
        repoRequest.setName(name);
        repoRequest.setDescription(description);
        repoRequest.setHomepage(homePage);

        mDisposable.add(mIGHRepository.createRepo(mToken.getValue(), repoRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoResponse ->
                {
                    if (callBack != null) {
                        callBack.onSuccessRepoCreation();
                    }
                }, throwable -> {
                    if (callBack != null) {
                        if (throwable instanceof HttpException && ((HttpException) throwable).code() == 401) {
                            callBack.onAuthErrorRepoCreation();
                        } else {
                            //todo сделать кастомизацию ошибок
                            callBack.onCommonErrorRepoCreation();
                        }
                    }
                    Timber.e(throwable);
                }));
    }

}
