package com.funnow.mygitapp;

import android.app.Application;

import com.funnow.mygitapp.di.DaggerDataComponent;
import com.funnow.mygitapp.di.DataComponent;
import com.funnow.mygitapp.di.RetrofitModule;

public class MyGitApplication extends Application {

    private static MyGitApplication application;
    DataComponent dataComponent;

    public static MyGitApplication getApplication() {
        return application;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        dataComponent = DaggerDataComponent.builder().retrofitModule(new RetrofitModule(this)).build();
        dataComponent.inject(this);
    }
}
