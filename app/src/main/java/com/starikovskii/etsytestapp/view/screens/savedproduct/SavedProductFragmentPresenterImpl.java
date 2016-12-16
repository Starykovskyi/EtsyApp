package com.starikovskii.etsytestapp.view.screens.savedproduct;

import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.model.dao.IProductDAO;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public class SavedProductFragmentPresenterImpl implements ISavedProductFragmentPresenter{
    private ISavedProductFragmentView view;

    @Inject
    IProductDAO dao;

    @Override
    public void loadProducts() {
        loadMoreProducts(0);
    }

    @Inject
    public SavedProductFragmentPresenterImpl (){

    }
    @Override
    public void loadMoreProducts(int offset) {
        view.showProgressIndicator();
        dao.getProducts(offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressIndicator();
                    }

                    @Override
                    public void onNext(List<ProductModel> response) {
                        view.hideProgressIndicator();
                        if(response.size()!=0){
                            view.onLoadNewProducts(response);
                        } else {
                            view.onEmptyResult();
                        }
                    }
                });
    }

    @Override
    public void deleteProduct(ProductModel product, final int position) {
        dao.deleteProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressIndicator();
                    }

                    @Override
                    public void onNext(Void response) {
                        view.hideProgressIndicator();
                        view.onDeleteProduct(position);
                    }
                });
    }

    @Override
    public void onProductClick(ProductModel product) {
        view.replaceToDetailFragment(product);
    }

    @Override
    public void init(ISavedProductFragmentView view) {
        this.view = view;
        EtsyApplication.getMainComponent().inject(this);
    }

}
