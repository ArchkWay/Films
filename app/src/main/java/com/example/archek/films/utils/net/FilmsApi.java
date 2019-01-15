package com.example.archek.films.utils.net;



import com.example.archek.films.utils.mocks.ObjectListResponse;
import com.example.archek.films.utils.mocks.ObjectResponse;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FilmsApi {
/*interface with the get-requests*/
    @GET("films.json")
    Observable<ObjectListResponse> getFilms();

}
