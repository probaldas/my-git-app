package com.funnow.mygitapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.services.GitApiDataSource;
import com.funnow.mygitapp.services.GitApiDataSourceFactory;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    @Inject
    GitApiDataSourceFactory dataSourceFactory;
    private LiveData<PagedList<GitCommits>> commitsPagedList;
    private PagedList.Config config;

    public MainViewModel() {
        MyGitApplication.getApplication().getDataComponent().inject(this);
        //LiveData<PageKeyedDataSource<Integer, GitCommits>> liveDataSource = dataSourceFactory.getDataSourceMutableLiveData();

         config = (new PagedList.Config.Builder())
                .setPageSize(GitApiDataSource.PAGE_SIZE)
                .build();
    }

    public LiveData<PagedList<GitCommits>> getCommitsPagedList() {
        commitsPagedList = (new LivePagedListBuilder(dataSourceFactory, config)).build();
        return commitsPagedList;
    }
}
