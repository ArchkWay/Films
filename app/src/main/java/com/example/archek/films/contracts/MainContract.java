package com.example.archek.films.contracts;


import com.example.archek.films.utils.mocks.ObjectListResponse;
import com.example.archek.films.utils.mocks.ObjectResponse;

import java.util.List;

import io.reactivex.Observable;

public interface MainContract {
    /*interfaces for mvp*/
    interface view{
        void setFilms(ObjectListResponse objectListResponse);

    }
    interface presenter extends Presenter<view>{

    }

    interface model {
        Observable<ObjectListResponse> getFilms();
    }
}
