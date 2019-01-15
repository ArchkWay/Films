package com.example.archek.films.di;

import android.app.Application;
import android.content.Context;

import com.example.archek.films.di.components.AppComponent;
import com.example.archek.films.di.components.DaggerAppComponent;
import com.example.archek.films.di.moduls.MvpModule;


public class BaseApp extends Application {
    /*baseDI*/
    private AppComponent appComponent;

    public AppComponent getInjector() {
        if(appComponent == null){
            appComponent = DaggerAppComponent
                    .builder()
                    .mvpModule(new MvpModule(this))
                    .build();
        }
        return appComponent;
    }
    public static BaseApp get(Context ctx){
            return (BaseApp)ctx.getApplicationContext();
    }
}
