package com.starikovskii.etsytestapp.view.screens.products;

import android.util.Log;

import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.network.response.ProductResponseModel;
import com.starikovskii.etsytestapp.network.IProductAPI;
import com.starikovskii.etsytestapp.utils.Constants;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public class ProductGridFragmentPresenterImpl implements IProductGridFragmentPresenter {

    private String TAG = ProductGridFragmentPresenterImpl.class.getSimpleName();

    private IProductGridFragmentView view;

    @Inject
    IProductAPI api;

    @Inject
    public ProductGridFragmentPresenterImpl (){

    }

    @Override
    public void init(IProductGridFragmentView view) {
        this.view = view;
        EtsyApplication.getMainComponent().inject(this);
    }

    @Override
    public void loadProducts(String categoryName, String keyword) {
        loadMoreProducts(categoryName, keyword, 0);
    }

    @Override
    public void loadMoreProducts(String categoryName, String keyword, int offset) {
        view.showProgressIndicator();
        api.getProducts(Constants.Network.API_KEY, categoryName, keyword, Constants.Network.MAIN_IMAGE_INCLUDES, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressIndicator();
                    }

                    @Override
                    public void onNext(ProductResponseModel response) {
                        Log.d(TAG,"onNext");
                        view.hideProgressIndicator();
                        view.onLoadNewProducts(response);
                    }
                });
    }

    @Override
    public void onProductClick(ProductModel product) {
        view.replaceToDetailFragment(product);
    }

}
