package com.example.archek.films.mvp;


import android.support.annotation.NonNull;

import com.example.archek.films.mocks.ObjectListResponse;
import com.example.archek.films.net.KinoService;
import com.example.archek.films.net.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmsPresenter {

    private final View view;
    private final KinoService api = RestApi.createService(KinoService.class);


    public FilmsPresenter(View view) {
        this.view = view;
    }

    public void loadMocks() {
        view.showProgress();
        Call<ObjectListResponse> call = api.getFilms();
        /*in background process are loading our mocks*/
        call.enqueue(new Callback<ObjectListResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectListResponse> call, @NonNull Response<ObjectListResponse> response) {
                view.showMocks(response.body());
                view.hideProgress();
            }

            @Override
            public void onFailure(@NonNull Call <ObjectListResponse> call, @NonNull Throwable t) {
                view.showError("Error");
                view.hideProgress();
            }
        });
    }

    public interface View{
        void showMocks(ObjectListResponse objectListResponse);
        void hideProgress();
        void showProgress();
        void showError(String error);
    }

}
