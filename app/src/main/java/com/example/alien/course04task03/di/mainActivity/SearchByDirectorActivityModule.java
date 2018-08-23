package com.example.alien.course04task03.di.mainActivity;


import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.di.commonActivity.CommonActivityModule;
import com.example.alien.course04task03.ui.common.BaseViewModel;


public class SearchByDirectorActivityModule extends CommonActivityModule {


    public SearchByDirectorActivityModule(AppCompatActivity activity, String scopeName, int type) {
       super(activity, scopeName, type);
        //todo сделать интерфейсы

        bind(BaseViewModel.class).toProvider(SearchByDirectorViewModelProvider.class).providesSingletonInScope();
       // bind(SearchByDirectorViewModel.class).toProvider(SearchByDirectorViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.director_search_title);
    }

}
