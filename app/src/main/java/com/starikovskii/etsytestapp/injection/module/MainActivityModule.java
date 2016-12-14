package com.starikovskii.etsytestapp.injection.module;

import com.starikovskii.etsytestapp.screens.IMainActivityPresenter;
import com.starikovskii.etsytestapp.screens.main.IMainFragmentPresenter;
import com.starikovskii.etsytestapp.screens.details.IProductDetailFragmentPresenter;
import com.starikovskii.etsytestapp.screens.products.IProductGridFragmentPresenter;
import com.starikovskii.etsytestapp.screens.savedproduct.ISavedProductFragmentPresenter;
import com.starikovskii.etsytestapp.screens.search.ISearchFragmentPresenter;
import com.starikovskii.etsytestapp.screens.MainActivityPresenterImpl;
import com.starikovskii.etsytestapp.screens.main.MainFragmentPresenterImpl;
import com.starikovskii.etsytestapp.screens.details.ProductDetailFragmentPresenterImpl;
import com.starikovskii.etsytestapp.screens.products.ProductGridFragmentPresenterImpl;
import com.starikovskii.etsytestapp.screens.savedproduct.SavedProductFragmentPresenterImpl;
import com.starikovskii.etsytestapp.screens.search.SearchFragmentPresenterImpl;
import com.starikovskii.etsytestapp.screens.IMainActivityView;

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
