package com.funnow.mygitapp.services;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.funnow.mygitapp.MyGitApplication;
import com.funnow.mygitapp.helper.ErrorUtils;
import com.funnow.mygitapp.models.ApiError;
import com.funnow.mygitapp.models.GitCommits;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitApiDataSource extends PageKeyedDataSource<Integer, GitCommits> {

    private static final String TAG = GitApiDataSource.class.getSimpleName();
    private static final String CONTENT_TYPE = "application/vnd.github.VERSION.sha";
    private static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 10;

    private final WebService webService;
    private final ErrorUtils errorUtils;

    @Inject
    public GitApiDataSource(WebService webService, ErrorUtils errorUtils) {
        this.webService = webService;
        this.errorUtils = errorUtils;
    }

    /*
    * Loading initial set of data (first 10 commits)
    * */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, GitCommits> callback) {
        webService.getCommits(CONTENT_TYPE, FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<List<GitCommits>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<GitCommits>> call,
                                           @NonNull Response<List<GitCommits>> response) {
                        Log.i(TAG, "loadInitial called for " + FIRST_PAGE + " page");
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                callback.onResult(response.body(), null, FIRST_PAGE + 1);
                            }
                        } else {
                            ApiError error = errorUtils.parseError(response);
                            showErrorToast(error.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<GitCommits>> call,
                                          @NonNull Throwable t) {
                        showErrorToast("Error - " + t.getMessage());
                    }
                });
    }

    /*
    * Loading previous data set (previous 10 commits)
    * */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GitCommits> callback) {
        webService.getCommits(CONTENT_TYPE, params.key, PAGE_SIZE)
                .enqueue(new Callback<List<GitCommits>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<GitCommits>> call,
                                           @NonNull Response<List<GitCommits>> response) {
                        Log.i(TAG, "loadInitial called for " + params.key + " page");
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Integer key = (params.key > 1) ? params.key - 1 : null;
                                callback.onResult(response.body(), key);
                            }
                        } else {
                            ApiError error = errorUtils.parseError(response);
                            showErrorToast(error.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<GitCommits>> call,
                                          @NonNull Throwable t) {
                        showErrorToast("Error - " + t.getMessage());
                    }
                });
    }

    /*
    * Loading next set of data (next 10 commits)
    * */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GitCommits> callback) {
        webService.getCommits(CONTENT_TYPE, params.key, PAGE_SIZE)
                .enqueue(new Callback<List<GitCommits>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<GitCommits>> call,
                                           @NonNull Response<List<GitCommits>> response) {
                        Log.i(TAG, "loadInitial called for " + params.key + " page");
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Integer key = response.body().size() != 0 ? params.key + 1 : null;
                                callback.onResult(response.body(), key);
                            }
                        } else {
                            ApiError error = errorUtils.parseError(response);
                            showErrorToast(error.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<GitCommits>> call,
                                          @NonNull Throwable t) {
                        showErrorToast("Error - " + t.getMessage());
                    }
                });
    }

    /*
    * Helper method for Error Toast
    * */
    private void showErrorToast(String error) {
        Toast.makeText(MyGitApplication.getApplication().getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }
}
