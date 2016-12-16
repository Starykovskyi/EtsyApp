package com.starikovskii.etsytestapp.view.screens.products;

import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.view.base.IBaseFragmentPresenter;

public interface IProductGridFragmentPresenter extends IBaseFragmentPresenter<IProductGridFragmentView> {
    void loadProducts(String categoryName, String keyword);
    void loadMoreProducts(String categoryName, String keyword, int offset);
    void onProductClick(ProductModel product);
}
