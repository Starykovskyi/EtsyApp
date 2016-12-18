package com.starikovskii.etsytestapp.view.screens.savedproduct;

import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.view.base.IBaseFragmentPresenter;

public interface ISavedProductFragmentPresenter extends IBaseFragmentPresenter<ISavedProductFragmentView> {
    void loadProducts();
    void loadMoreProducts(int offset);
    void deleteProduct(ProductModel product, int position);
    void onProductClick(ProductModel product);
    void getProductCount();
}