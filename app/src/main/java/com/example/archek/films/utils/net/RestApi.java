package com.example.archek.films.utils.net;




import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

final public class RestApi {
    private final Retrofit retrofit;

    public RestApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public FilmsApi getApi() {
        return retrofit.create(FilmsApi.class);
    }

}
