package com.example.archek.films.views.filminfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.films.R;
import com.example.archek.films.utils.mocks.ObjectResponse;
import com.squareup.picasso.Picasso;

public class FilmInfoActivity extends AppCompatActivity {

    private static final String EXTRA_RU_NAME = "EXTRA_RU_NAME";
    private static final String EXTRA_ENG_NAME = "EXTRA_ENG_NAME";
    private static final String EXTRA_URL_PIC = "EXTRA_URL_PIC";
    private static final String EXTRA_FILM_YEAR = "EXTRA_FILM_YEAR";
    private static final String EXTRA_FILM_RATING = "EXTRA_FILM_RATING";
    private static final String EXTRA_FILM_DESCRIPTION = "EXTRA_FILM_DESCRIPTION";
    Toolbar toolbar;
    TextView tvTitle;

    /*get the extras from mainactivity*/
    public static Intent makeIntent(Context context, ObjectResponse film) {
        return new Intent( context, FilmInfoActivity.class )
                .putExtra(FilmInfoActivity.EXTRA_ENG_NAME, film.getName())
                .putExtra(FilmInfoActivity.EXTRA_RU_NAME, film.getLocalizedName())
                .putExtra(FilmInfoActivity.EXTRA_URL_PIC, film.getImageUrl())
                .putExtra(FilmInfoActivity.EXTRA_FILM_YEAR, film.getYear())
                .putExtra(FilmInfoActivity.EXTRA_FILM_RATING, film.getRating())
                .putExtra(FilmInfoActivity.EXTRA_FILM_DESCRIPTION, film.getDescription());
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filminfo);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle = findViewById(R.id.tvTitle);



        /*bearing from extras to strings*/
        Intent intent = getIntent();
        String urlPic = intent.getStringExtra(EXTRA_URL_PIC);
        String engName = intent.getStringExtra(EXTRA_ENG_NAME);//Перекладываем из экстра строк в локальные
        String ruName = intent.getStringExtra(EXTRA_RU_NAME);     //Replace from EXTRA to locals variables
        String filmYear = "Год:  " + intent.getStringExtra(EXTRA_FILM_YEAR);
        String filmRating = "Рейтинг:  " + intent.getStringExtra(EXTRA_FILM_RATING);
        String filmDes = "Описание: " + intent.getStringExtra(EXTRA_FILM_DESCRIPTION);
        /*initiate the views*/
        ImageView ivPic = findViewById(R.id.ivPic);
        TextView tvFilmEngName = findViewById(R.id.tvEngName);
        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvRating = findViewById(R.id.tvRating);
        TextView tvDes = findViewById(R.id.tvDes);
        tvDes.setMovementMethod(new ScrollingMovementMethod());
        tvFilmEngName.setMovementMethod(new ScrollingMovementMethod());
        tvTitle.setMovementMethod(new ScrollingMovementMethod());

        if (filmRating.equals("Рейтинг:  null")){
            filmRating = "Нет рейтинга";
        }
        if (filmDes.equals("Описание: null")){
            filmDes = "Нет описания";
            float size = tvDes.getTextSize()*1.5f;
            tvDes.setTextSize(size);
        }
        /*fill the views from strings */
        Picasso.get()
                .load(urlPic)
                .placeholder(R.drawable.photo)
                .into(ivPic);
        tvTitle.setText(ruName);

        tvFilmEngName.setText(engName);
        tvYear.setText(filmYear);
        tvRating.setText(filmRating);
        tvDes.setText(filmDes);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
