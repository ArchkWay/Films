package com.example.archek.films.net;



import com.example.archek.films.mocks.ObjectListResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface KinoService {
/*interface with the get-requests*/
    @GET("films.json")
        Call<ObjectListResponse> getFilms();

}
