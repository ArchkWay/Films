package com.example.archek.films.di.components;


import com.example.archek.films.di.moduls.MvpModule;
import com.example.archek.films.models.MainModel;
import com.example.archek.films.presenters.MainPresenter;
import com.example.archek.films.views.FilmsActivity;

import javax.inject.Singleton;

import dagger.Component;

/*places for injecting*/
@Singleton
@Component(modules = {MvpModule.class})
public interface AppComponent {
    void inject(FilmsActivity activity);
    void inject(MainPresenter presenter);
    void inject(MainModel model);
}
