package com.starikovskii.etsytestapp.screens.products;

import com.starikovskii.etsytestapp.model.CategoriesModel;
import com.starikovskii.etsytestapp.model.PaginationModel;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.model.ProductResponseModel;

import java.util.List;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public interface IProductGridFragmentView {
    void onLoadNewProduct(ProductResponseModel response);
    void replaceToDetailFragment(ProductModel product);
    void showProgressIndicator();
    void hideProgressIndicator();
}
