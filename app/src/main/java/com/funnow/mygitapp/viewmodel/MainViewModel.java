package com.funnow.mygitapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.funnow.mygitapp.MyGitApplication;
import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.services.GitApiDataSource;
import com.funnow.mygitapp.services.GitApiDataSourceFactory;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    @Inject
    GitApiDataSourceFactory dataSourceFactory;
    private PagedList.Config config;

    public MainViewModel() {
        MyGitApplication.getApplication().getDataComponent().inject(this);

        config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(GitApiDataSource.PAGE_SIZE)
                .build();
    }

    public LiveData<PagedList<GitCommits>> getCommitsPagedList() {
        return (LiveData<PagedList<GitCommits>>)(new LivePagedListBuilder(dataSourceFactory, config)).build();
    }
}
