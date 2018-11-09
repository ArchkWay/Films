package com.example.archek.films.net;

import com.example.archek.films.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final public class RestApi {
    private static final String BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/";// api link
    private static final long CONNECT_TIMEOUT = 60;//defaults: delay 60 msec
    private static final long READ_TIMEOUT =60;
    //instal retrofit builder
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl( BASE_URL )
            .validateEagerly(true)
            .client( buildOkHttpClient() )
            .addConverterFactory( createConverterFactory() )
            .build();

    public static <S> S createService(Class<S> klass) {//metod for load data
        return retrofit.create( klass );
    }
    //setup Gson converter
    private static Converter.Factory createConverterFactory() {
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES )
                        .create()
        );
    }
    //setup OkHttpClient
    private static OkHttpClient buildOkHttpClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout( READ_TIMEOUT,TimeUnit.SECONDS );
        if(BuildConfig.DEBUG){
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor( interceptor );
        }
        return builder.build();
    }
}
