package com.starikovskii.etsytestapp.screens.products;

import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.utils.IBaseFragmentPresenter;

public interface IProductGridFragmentPresenter extends IBaseFragmentPresenter<IProductGridFragmentView> {
    void loadProducts(String categoryName, String keyword);
    void loadMoreProducts(String categoryName, String keyword, int offset);
    void onItemClick(ProductModel product);
}
