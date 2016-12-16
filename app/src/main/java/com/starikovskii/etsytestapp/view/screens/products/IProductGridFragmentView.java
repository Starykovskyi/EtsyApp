package com.starikovskii.etsytestapp.view.screens.products;

import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.network.response.ProductResponseModel;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public interface IProductGridFragmentView {
    void onLoadNewProducts(ProductResponseModel response);
    void replaceToDetailFragment(ProductModel product);
    void showProgressIndicator();
    void hideProgressIndicator();
}
