package com.example.archek.films.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.archek.films.R;
import com.example.archek.films.contracts.MainContract;
import com.example.archek.films.di.BaseApp;
import com.example.archek.films.utils.FilmsAdapter;
import com.example.archek.films.utils.mocks.ObjectListResponse;
import com.example.archek.films.utils.mocks.ObjectResponse;
import com.example.archek.films.views.filminfo.FilmInfoActivity;

import java.util.List;

import javax.inject.Inject;

public class FilmsActivity extends AppCompatActivity implements MainContract.view, FilmsAdapter.Callback{

    private FilmsAdapter adapter;
    @Inject MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        /*get recyclerview parts, presenter and data objects from it*/
        RecyclerView rvList = findViewById(R.id.rvFilms);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FilmsAdapter(this);
        rvList.setAdapter(adapter);
        BaseApp.get(this).getInjector().inject(this);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        /*if destroy - detach view*/
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onFilmClick(ObjectResponse film) {
        Intent intent = FilmInfoActivity.makeIntent(this, film);
        startActivity(intent);
    }

    @Override
    public void setFilms(ObjectListResponse objectListResponse) {
        adapter.replaceAll(objectListResponse);
        adapter.notifyDataSetChanged();
    }
}
