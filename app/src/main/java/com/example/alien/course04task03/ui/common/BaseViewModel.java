package com.example.alien.course04task03.ui.common;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course04task03.data.IRepoRepository;
import com.example.alien.course04task03.data.model.RepoSimple;
import com.example.alien.course04task03.data.model.RepoSimple;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseViewModel extends ViewModel {
    protected MutableLiveData<List<RepoSimple>> mRepoList = new MutableLiveData<>();
  //  protected OrderedRealmCollection<RepoSimple> data;
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>();

    protected IRepoRepository mRepository;

    private Gson mGson;

    public BaseViewModel(IRepoRepository repository, Gson gson) {
        this.mRepository = repository;
        this.mGson = gson;

        EventBus.getDefault().register(this);

        mRepoList.observeForever(list ->
        {
            mIsEmpty.postValue(list != null && list.isEmpty());

//            if (list instanceof RealmResults) {
//                RealmResults<RepoSimple> RepoSimpleRealmResults = (RealmResults<RepoSimple>) list;
//                RepoSimpleRealmResults.addChangeListener(RepoSimples -> mIsEmpty.postValue(RepoSimples.isEmpty()));
//            }
        });
    }

    public MutableLiveData<List<RepoSimple>> getRepoList() {
        return mRepoList;
    }

    public MutableLiveData<Boolean> getIsEmpty() {
        return mIsEmpty;
    }

    public void generateData(String json) {
        Type type = new TypeToken<List<RepoSimple>>() {
        }.getType();
        List<RepoSimple> RepoSimples = mGson.fromJson(json, type);
        mRepository.insertItems(RepoSimples);
    }

    public void deleteItem(long id) {
        mRepository.deleteItem(id);
    }

//    public OrderedRealmCollection<RepoSimple> getData() {
//        return data;
//    }

    @Override
    protected void onCleared() {
        EventBus.getDefault().unregister(this);
        super.onCleared();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRepoSimpleDataBaseUpdate(IRepoRepository.IOnRepoSimpleDataBaseUpdate event)
    {
        updateFromRepository();
    }

    abstract protected void updateFromRepository();
}
