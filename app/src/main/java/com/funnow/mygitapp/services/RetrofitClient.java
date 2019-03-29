package com.funnow.mygitapp.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(String baseUrl) {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            httpBuilder.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
