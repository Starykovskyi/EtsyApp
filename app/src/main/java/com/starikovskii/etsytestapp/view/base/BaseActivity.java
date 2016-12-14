package com.starikovskii.etsytestapp.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.injection.component.IEtsyMainComponent;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(EtsyApplication.get(this).getAppComponent());
    }

    protected abstract void setupComponent(IEtsyMainComponent appComponent);
}
