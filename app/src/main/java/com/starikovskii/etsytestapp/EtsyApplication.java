package com.starikovskii.etsytestapp;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.starikovskii.etsytestapp.injection.component.DaggerIEtsyMainComponent;
import com.starikovskii.etsytestapp.injection.component.IEtsyMainComponent;
import com.starikovskii.etsytestapp.injection.module.AppModule;
import com.starikovskii.etsytestapp.injection.module.DAOModule;
import com.starikovskii.etsytestapp.injection.module.NetworkModule;


public class EtsyApplication extends Application {

    private static IEtsyMainComponent appComponent;

    public static EtsyApplication get(Context context) {
        return (EtsyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        buildGraphAndInject();
    }

    public static IEtsyMainComponent getMainComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerIEtsyMainComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .dAOModule(new DAOModule())
                .build();
        appComponent.inject(this);
    }
}
