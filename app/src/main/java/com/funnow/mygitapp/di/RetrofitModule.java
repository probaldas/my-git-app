package com.funnow.mygitapp.di;

import android.app.Application;

import com.funnow.mygitapp.helper.ErrorUtils;
import com.funnow.mygitapp.services.GitApiDataSource;
import com.funnow.mygitapp.services.GitApiDataSourceFactory;
import com.funnow.mygitapp.services.WebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private static final String BASE_URL = "https://api.github.com/";

    private Application application;

    public RetrofitModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    final HttpLoggingInterceptor provideInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    final OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    final Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    final WebService provideWebService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }

    @Provides
    final ErrorUtils provideErrorUtils(Retrofit retrofit) {
        return new ErrorUtils(retrofit);
    }

    @Provides
    final GitApiDataSource provideDataSource(WebService webService, ErrorUtils errorUtils) {
        return new GitApiDataSource(webService, errorUtils);
    }

    @Provides
    @Singleton
    final GitApiDataSourceFactory provideDataSourceFactory(GitApiDataSource gitApiDataSource) {
        return new GitApiDataSourceFactory(gitApiDataSource);
    }
}
