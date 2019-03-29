package com.funnow.mygitapp.services;

import com.funnow.mygitapp.models.GitCommits;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface WebService {

    @GET("repos/probaldas/my-git-app/commits")
    Call<List<GitCommits>> getCommits(@Header("Accept") String contentType);

}
