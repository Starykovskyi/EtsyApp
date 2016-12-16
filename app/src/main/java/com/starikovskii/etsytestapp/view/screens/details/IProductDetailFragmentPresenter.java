package com.starikovskii.etsytestapp.view.screens.details;

import android.widget.ImageView;

import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.view.base.IBaseFragmentPresenter;

public interface IProductDetailFragmentPresenter extends IBaseFragmentPresenter<IProductDetailFragmentView>{
    void saveProduct(ProductModel productModel);
    void setImageToView(String url, ImageView imageView);
}
