package com.funnow.mygitapp.services;

import android.widget.Toast;

import com.funnow.mygitapp.MyGitApplication;
import com.funnow.mygitapp.helper.ErrorUtils;
import com.funnow.mygitapp.models.ApiError;
import com.funnow.mygitapp.models.GitCommits;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitApiRepository {

    private static final String CONTENT_TYPE = "application/vnd.github.VERSION.sha";
    private WebService webService;
    private ErrorUtils errorUtils;

    @Inject
    public GitApiRepository(WebService webService, ErrorUtils errorUtils) {
        this.webService = webService;
        this.errorUtils = errorUtils;
    }

    public MutableLiveData<List<GitCommits>> getAllCommits() {
        final MutableLiveData<List<GitCommits>> allCommits = new MutableLiveData<>();

        Call<List<GitCommits>> call = webService.getCommits(CONTENT_TYPE);
        call.enqueue(new Callback<List<GitCommits>>() {
            @Override
            public void onResponse(Call<List<GitCommits>> call, Response<List<GitCommits>> response) {
                if (response.isSuccessful()) {
                    allCommits.setValue(response.body());
                } else {
                    allCommits.setValue(null);
                    ApiError error = errorUtils.parseError(response);
                    Toast.makeText(MyGitApplication.getApplication().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<GitCommits>> call, Throwable t) {
                allCommits.setValue(null);
                Toast.makeText(MyGitApplication.getApplication().getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
            }
        });

        return allCommits;
    }
}
