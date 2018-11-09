package com.example.archek.films;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.example.archek.films.net.KinoService;
import com.example.archek.films.model.ObjectListResponse;
import com.example.archek.films.model.ObjectResponse;
import com.example.archek.films.net.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecAdapter.Callback {
    private Call<ObjectListResponse> call;
    private final KinoService service = RestApi.createService( KinoService.class );//initiate the recyclerview
    private RecAdapter adapter = new RecAdapter(this);
    private RecyclerView rvFilms;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*setup the list(in recyclerview)/adapter*/
        rvFilms = findViewById(R.id.rvFilms);
        final RecyclerView.LayoutManager lm = new LinearLayoutManager( this );
        rvFilms.setLayoutManager( lm );
        rvFilms.setAdapter( adapter );
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar( toolbar );
        /*call all films through the adapter*/
        call = service.getFilms();
        call.enqueue( new Callback<ObjectListResponse>() {
            @Override
            public void onResponse(Call <ObjectListResponse> call, Response<ObjectListResponse> response) {
                ObjectListResponse objectListResponse = response.body();
                adapter.replaceAll(objectListResponse);
            }
            @Override
            public void onFailure(Call <ObjectListResponse> call, Throwable t) {
                if(call.isCanceled()){
                    Toast.makeText( MainActivity.this, R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
    //clicklistener for launch FilmInfo activity
    @Override
    public void onFilmClick(ObjectResponse film) {
        Intent intent = FilmInfoActivity.makeIntent(this, film);
        startActivity(intent);
    }
}