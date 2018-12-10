package com.example.archek.films;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.films.model.ObjectResponse;
import com.squareup.picasso.Picasso;

public class FilmInfoActivity extends Activity {

    private static final String EXTRA_RU_NAME = "EXTRA_RU_NAME";
    private static final String EXTRA_ENG_NAME = "EXTRA_ENG_NAME";
    private static final String EXTRA_URL_PIC = "EXTRA_URL_PIC";
    private static final String EXTRA_FILM_YEAR = "EXTRA_FILM_YEAR";
    private static final String EXTRA_FILM_RATING = "EXTRA_FILM_RATING";
    private static final String EXTRA_FILM_DESCRIPTION = "EXTRA_FILM_DESCRIPTION";

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filminfo);
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
        ImageView ivBack = findViewById(R.id.ivBack);
        TextView tvRuName = findViewById(R.id.tvRuName);
        TextView tvFilmEngName = findViewById(R.id.tvEngName);
        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvRating = findViewById(R.id.tvRating);
        TextView tvDes = findViewById(R.id.tvDes);
        ConstraintLayout clInfo = findViewById(R.id.clInfo);
        if(Build.VERSION.SDK_INT >= 24) {
            clInfo.setBackground(getResources().getDrawable(R.drawable.backitem));
        }
        tvDes.setMovementMethod(new ScrollingMovementMethod());
        tvFilmEngName.setMovementMethod(new ScrollingMovementMethod());

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
        tvRuName.setText(ruName);
        tvFilmEngName.setText(engName);
        tvYear.setText(filmYear);
        tvRating.setText(filmRating);
        tvDes.setText(filmDes);
        /*//Comeback to MainActivity*/
        ivBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBtn = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(returnBtn);
            }
        } );
    }

}
