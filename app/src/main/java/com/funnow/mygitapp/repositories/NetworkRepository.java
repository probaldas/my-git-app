package com.funnow.mygitapp.repositories;

import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.services.WebService;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {

    private static final String CONTENT_TYPE = "application/vnd.github.VERSION.sha";
    private WebService webService;

    @Inject
    public NetworkRepository(WebService webService) {
        this.webService = webService;
    }

    public MutableLiveData<List<GitCommits>> getAllCommits() {
        final MutableLiveData<List<GitCommits>> allCommits = new MutableLiveData<>();

        Call<List<GitCommits>> call = webService.getCommits(CONTENT_TYPE);
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
