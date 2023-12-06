package com.funnow.mygitapp.services;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.funnow.mygitapp.models.GitCommits;

import javax.inject.Inject;

@SuppressWarnings("ALL")
public class GitApiDataSourceFactory extends DataSource.Factory {

    private final GitApiDataSource mGitApiDataSource;
    private final MutableLiveData<PageKeyedDataSource<Integer, GitCommits>> dataSourceMutableLiveData
            = new MutableLiveData<>();

    @Inject
    public GitApiDataSourceFactory(GitApiDataSource gitApiDataSource) {
        this.mGitApiDataSource = gitApiDataSource;
    }

    @Override
    public DataSource create() {
        dataSourceMutableLiveData.postValue(mGitApiDataSource);
        return mGitApiDataSource;
    }

}
