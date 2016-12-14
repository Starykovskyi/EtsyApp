package com.starikovskii.etsytestapp.injection.module;

import android.app.Application;
import android.content.Context;

import com.starikovskii.etsytestapp.EtsyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by FkingAlive on 12.12.2016.
 */
@Module
public class AppModule {
    private final EtsyApplication app;

    public AppModule(EtsyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app.getBaseContext();
    }
}
