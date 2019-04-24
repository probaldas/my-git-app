package com.funnow.mygitapp.services;

import com.funnow.mygitapp.models.GitCommits;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WebService {

    @GET("repos/probaldas/my-git-app/commits")
    Call<List<GitCommits>> getCommits(
            @Header("Accept") String contentType,
            @Query("page") int pageNumber,
            @Query("per_page") int commitsPerPage);

}
