package com.funnow.mygitapp.di;

import com.funnow.mygitapp.CommitViewModel;
import com.funnow.mygitapp.MainViewModel;
import com.funnow.mygitapp.MyGitApplication;
import com.funnow.mygitapp.services.GitApiDataSourceFactory;
import com.funnow.mygitapp.services.GitApiRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface DataComponent {
    void inject(MyGitApplication application);

    void inject(GitApiRepository gitApiRepository);

    void inject(GitApiDataSourceFactory gitApiDataSourceFactory);

    void inject(MainViewModel mainViewModel);

    void inject(CommitViewModel commitViewModel);
}
