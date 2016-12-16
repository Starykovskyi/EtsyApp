package com.starikovskii.etsytestapp.injection.module;

import com.starikovskii.etsytestapp.view.screens.IMainActivityPresenter;
import com.starikovskii.etsytestapp.view.screens.main.IMainFragmentPresenter;
import com.starikovskii.etsytestapp.view.screens.details.IProductDetailFragmentPresenter;
import com.starikovskii.etsytestapp.view.screens.products.IProductGridFragmentPresenter;
import com.starikovskii.etsytestapp.view.screens.savedproduct.ISavedProductFragmentPresenter;
import com.starikovskii.etsytestapp.view.screens.search.ISearchFragmentPresenter;
import com.starikovskii.etsytestapp.view.screens.MainActivityPresenterImpl;
import com.starikovskii.etsytestapp.view.screens.main.MainFragmentPresenterImpl;
import com.starikovskii.etsytestapp.view.screens.details.ProductDetailFragmentPresenterImpl;
import com.starikovskii.etsytestapp.view.screens.products.ProductGridFragmentPresenterImpl;
import com.starikovskii.etsytestapp.view.screens.savedproduct.SavedProductFragmentPresenterImpl;
import com.starikovskii.etsytestapp.view.screens.search.SearchFragmentPresenterImpl;
import com.starikovskii.etsytestapp.view.screens.IMainActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by FkingAlive on 12.12.2016.
 */
@Module
public class MainActivityModule {
    private IMainActivityView view;

    public MainActivityModule(IMainActivityView view) {
        this.view = view;
    }

    @Provides
    public IMainActivityView provideView() {
        return view;
    }

    @Provides
    public IMainActivityPresenter provideMainActivityPresenter (IMainActivityView view){
        return  new MainActivityPresenterImpl(view);
    }

    @Provides
    public IMainFragmentPresenter provideMainFragmentPresenter() {
        return new MainFragmentPresenterImpl();
    }

    @Provides
    public IProductDetailFragmentPresenter provideProductDetailFragmentPresenter () {
        return new ProductDetailFragmentPresenterImpl();
    }

    @Provides
    public IProductGridFragmentPresenter provideProductGridFragmentPresenter(){
        return new ProductGridFragmentPresenterImpl();
    }

    @Provides
    public ISearchFragmentPresenter provideSearchFragmentPresenter(){
        return new SearchFragmentPresenterImpl();
    }

    @Provides
    public ISavedProductFragmentPresenter provideSavedProductFragmentPresenter(){
        return new SavedProductFragmentPresenterImpl();
    }
}
