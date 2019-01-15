package com.example.archek.films.contracts;

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
