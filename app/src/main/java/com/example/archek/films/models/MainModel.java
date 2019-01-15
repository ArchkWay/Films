package com.example.archek.films.models;

import android.content.Context;

import com.example.archek.films.contracts.MainContract;
//import com.example.archek.films.di.BaseApp;
import com.example.archek.films.di.BaseApp;
import com.example.archek.films.utils.mocks.ObjectListResponse;
import com.example.archek.films.utils.mocks.ObjectResponse;
import com.example.archek.films.utils.net.FilmsApi;
import com.example.archek.films.utils.net.RestApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainModel implements MainContract.model{
//    /*get through retrofit mocks for further using -> provider -> view*/
    private final FilmsApi api;
    @Inject RestApi provider ;

    public MainModel(Context context){
        BaseApp.get(context).getInjector().inject(this);
        api = provider.getApi();
    }

    @Override
    public Observable<ObjectListResponse> getFilms() {
        return api.getFilms().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
