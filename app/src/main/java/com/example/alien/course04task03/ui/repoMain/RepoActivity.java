package com.example.alien.course04task03.ui.repoMain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.di.gitHubRepository.GitHubRepositoryModule;
import com.example.alien.course04task03.di.mainActivity.MainActivityModule;
import com.example.alien.course04task03.di.mainActivity.SearchByNameActivityModule;
import com.example.alien.course04task03.ui.event.CloseActivityEvent;
import com.example.alien.course04task03.ui.repoList.ListAllFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class RepoActivity extends AppCompatActivity {

    public static final int TYPE_LIST = 0;
    public static final int TYPE_SEARCH_BY_NAME = 1;
    private static final String TYPE_KEY = "SearchActivityTypeKey";

    @Inject
    RepoFragment mRepoFragment;

    @Inject
    ListAllFragment mListAllFragment;

    @Inject
    @Named("ID_TITLE")
    protected Integer mTitleId;

    private String mScopeName;

    @Inject
    @Named("USER")
    Single<User> mUser;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toothpickInject();

        setContentView(R.layout.ac_double_fragment);
        if (savedInstanceState == null) {
            changeFragment();
        }

        mUser.observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> setTitle(getString(mTitleId) +" "+ user.getLogin()),
                        Timber::e);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent();
        intent.setClass(context, RepoActivity.class);
        intent.putExtra(TYPE_KEY, type);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    protected void changeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mRepoFragment)
                .addToBackStack(mRepoFragment.getClass().getSimpleName())
                .replace(R.id.fragmentListContainer, mListAllFragment)
                .addToBackStack(mListAllFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         Toothpick.closeScope(mScopeName);
    }

    private void toothpickInject() {

        Module module;
        mScopeName = this.getClass().getSimpleName() + ".";
        int type = getIntent().getIntExtra(TYPE_KEY, 0);

        switch (type) {
            case TYPE_SEARCH_BY_NAME: {
                mScopeName = "SEARCH_BY_NAME_SCOPE";
                module = new SearchByNameActivityModule(this, mScopeName, type);
                break;
            }
            default: {
                mScopeName = "MAIN_SCOPE";
                module = new MainActivityModule(this, mScopeName, type);
                break;
            }
        }


        Scope gitHubScope = Toothpick.openScopes("Application", "AuthorizedScope");
        gitHubScope.installModules(new GitHubRepositoryModule(this));

        Scope scope = Toothpick.openScopes("AuthorizedScope", mScopeName);
        scope.installModules(module);
        Toothpick.inject(this, scope);

    }

    public String getScopeName() {
        return mScopeName;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClose(CloseActivityEvent closeActivityEvent){
        Toothpick.closeScope("AuthorizedScope");
        finish();
    }
}
