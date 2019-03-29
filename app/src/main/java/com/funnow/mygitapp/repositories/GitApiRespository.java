package com.funnow.mygitapp.repositories;

import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.services.RetrofitClient;
import com.funnow.mygitapp.services.WebService;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GitApiRespository {

    private static final String BASE_URL = "https://api.github.com/";
    private static final String CONTENT_TYPE = "application/vnd.github.VERSION.sha";

    public MutableLiveData<List<GitCommits>> getAllCommits() {
        final MutableLiveData<List<GitCommits>> allCommits = new MutableLiveData<>();

        Retrofit retrofit = RetrofitClient.getRetrofitClient(BASE_URL);
        WebService service = retrofit.create(WebService.class);

        Call<List<GitCommits>> call = service.getCommits(CONTENT_TYPE);
        call.enqueue(new Callback<List<GitCommits>>() {
            @Override
            public void onResponse(Call<List<GitCommits>> call, Response<List<GitCommits>> response) {
                if (response.isSuccessful()) {
                    allCommits.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GitCommits>> call, Throwable t) {
                allCommits.setValue(null);
            }
        });

        return allCommits;
    }

}
