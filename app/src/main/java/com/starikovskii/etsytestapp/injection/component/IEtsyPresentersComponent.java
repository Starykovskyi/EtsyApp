package com.starikovskii.etsytestapp.injection.component;

import com.starikovskii.etsytestapp.injection.ActivityScope;
import com.starikovskii.etsytestapp.injection.module.MainActivityModule;
import com.starikovskii.etsytestapp.screens.main.MainFragment;
import com.starikovskii.etsytestapp.screens.MainActivity;
import com.starikovskii.etsytestapp.screens.products.ProductGridFragment;
import com.starikovskii.etsytestapp.screens.search.SearchFragment;

import dagger.Component;
@ActivityScope
@Component(
        dependencies = IEtsyMainComponent.class,
        modules = { MainActivityModule.class})
public interface IEtsyPresentersComponent {
    void inject(MainFragment fragmentMain);
    void inject(MainActivity mainActivity);
    void inject(SearchFragment searchFragment);
    void inject(ProductGridFragment productGridFragment);
}
