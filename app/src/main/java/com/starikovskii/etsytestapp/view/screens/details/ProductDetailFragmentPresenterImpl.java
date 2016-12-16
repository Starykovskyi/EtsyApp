package com.starikovskii.etsytestapp.view.screens.details;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.model.dao.IProductDAO;
import com.starikovskii.etsytestapp.network.response.CategoriesResponseModel;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import butterknife.BindDimen;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public class ProductDetailFragmentPresenterImpl implements IProductDetailFragmentPresenter {
    private IProductDetailFragmentView view;

    @Inject
    IProductDAO dao;
    @Inject
    Context context;

    @Override
    public void saveProduct(ProductModel productModel) {
        dao.saveProduct(productModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onProductSavedError();
                    }

                    @Override
                    public void onNext(Long response) {
                        if (response >= 0) {
                            view.onProductSavedSuccess();
                        } else {
                            view.onProductSavedError();
                        }
                    }
                });
    }

    @Override
    public void setImageToView(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public void init(IProductDetailFragmentView view) {
        this.view = view;
        EtsyApplication.getMainComponent().inject(this);
    }
}
