package com.starikovskii.etsytestapp.view.screens.search;

import android.util.Log;

import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.model.CategoriesModel;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.model.dao.IProductDAO;
import com.starikovskii.etsytestapp.network.response.CategoriesResponseModel;
import com.starikovskii.etsytestapp.network.ICategoryAPI;
import com.starikovskii.etsytestapp.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public class SearchFragmentPresenterImpl implements ISearchFragmentPresenter {
    private String TAG = SearchFragmentPresenterImpl.class.getSimpleName();

    @Inject
    ICategoryAPI api;

    ISearchFragmentView view;

    @Inject
    public SearchFragmentPresenterImpl() {
    }


    @Override
    public void loadCategories() {
        view.showProgressIndicator();
        api.getCategories(Constants.Network.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoriesResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressIndicator();
                    }

                    @Override
                    public void onNext(CategoriesResponseModel response) {
                        Log.d(TAG,"onNext");
                        view.hideProgressIndicator();
                        view.setupCategoriesListAdapter(response.getResults());
                    }
                });
    }

    @Override
    public void onSubmitClick(CategoriesModel category, String keyword) {
        view.replaceToListFragment(category.getName(), keyword);
    }

    @Override
    public void init(ISearchFragmentView view) {
        this.view = view;
        EtsyApplication.getMainComponent().inject(this);
    }
}
