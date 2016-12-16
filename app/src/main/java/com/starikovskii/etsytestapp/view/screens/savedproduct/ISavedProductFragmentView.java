package com.starikovskii.etsytestapp.view.screens.savedproduct;

import com.starikovskii.etsytestapp.model.ProductModel;

import java.util.List;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public interface ISavedProductFragmentView {
    void onLoadNewProducts(List<ProductModel> products);
    void replaceToDetailFragment(ProductModel product);
    void showProgressIndicator();
    void hideProgressIndicator();
    void onDeleteProduct(int position);
    void onEmptyResult();

}
