package com.example.archek.films.presenters;


import android.content.Context;

import com.example.archek.films.contracts.MainContract;
import com.example.archek.films.di.BaseApp;

import javax.inject.Inject;

public class MainPresenter implements MainContract.presenter{
    MainContract.view view;
    @Inject MainContract.model model;

    @Inject
    public MainPresenter(Context context){
        BaseApp.get(context).getInjector().inject(this);
    }

    /*getting mocks from Model*/
    @Override
    public void attachView(MainContract.view view) {
        this.view = view;
        model.getFilms().subscribe(view::setFilms);
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
