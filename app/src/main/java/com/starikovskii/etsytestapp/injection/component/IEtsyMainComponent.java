package com.starikovskii.etsytestapp.injection.component;

/**
 * Created by FkingAlive on 12.12.2016.
 */

import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.injection.module.AppModule;
import com.starikovskii.etsytestapp.injection.module.NetworkModule;
import com.starikovskii.etsytestapp.screens.products.ProductGridFragmentPresenterImpl;
import com.starikovskii.etsytestapp.screens.search.SearchFragmentPresenterImpl;
import com.starikovskii.etsytestapp.view.adapters.ProductRecyclerViewAdapter;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = { AppModule.class, NetworkModule.class})
public interface IEtsyMainComponent {
    void inject(EtsyApplication app);
    void inject(SearchFragmentPresenterImpl searchFragmentPresenter);
    void inject(ProductGridFragmentPresenterImpl productGridFragmentPresenter);
    void inject(ProductRecyclerViewAdapter.ProductViewHolder productViewHolder);
}
