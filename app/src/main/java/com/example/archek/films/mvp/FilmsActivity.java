package com.example.archek.films.mvp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.archek.films.R;
import com.example.archek.films.mocks.ObjectListResponse;
import com.example.archek.films.mocks.ObjectResponse;
import com.example.archek.films.mvp.filminfo.FilmInfoActivity;

public class FilmsActivity extends AppCompatActivity implements FilmsAdapter.Callback, FilmsPresenter.View {
    private FilmsAdapter adapter = new FilmsAdapter(this);
    private RecyclerView rvFilms;
    private ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*setup the list(in recyclerview)/adapter*/
        rvFilms = findViewById(R.id.rvFilms);
        final RecyclerView.LayoutManager lm = new LinearLayoutManager( this );
        rvFilms.setLayoutManager(lm);
        rvFilms.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {

        /*recycler view stuff*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new FilmsAdapter(this);

        RecyclerView rvList = findViewById(R.id.rvFilms);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);
        /*launch presenter*/
        FilmsPresenter presenter = new FilmsPresenter(this);
        presenter.loadMocks();
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, R.string.error,Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void showMocks(ObjectListResponse objectListResponse) {
        adapter.replaceAll(objectListResponse);

    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    //clicklistener for launch FilmInfo activity
    @Override
    public void onFilmClick(ObjectResponse film) {
        Intent intent = FilmInfoActivity.makeIntent(this, film);
        startActivity(intent);
    }
}