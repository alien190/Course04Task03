package com.example.alien.course04task03.di.mainActivity;


import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.di.commonActivity.CommonActivityModule;
import com.example.alien.course04task03.ui.common.BaseViewModel;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;


public class MainActivityModule extends CommonActivityModule {

    public MainActivityModule(AppCompatActivity activity, String scopeName, int type) {
        super(activity, scopeName, type);
        //todo сделать интерфейсы
        bind(BaseViewModel.class).toProvider(ListAllViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("ID_TITLE").toInstance(R.string.main_activity_title);

    }

}
