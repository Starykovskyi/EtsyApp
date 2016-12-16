package com.starikovskii.etsytestapp.utils.listeners;

import com.starikovskii.etsytestapp.model.ProductModel;

/**
 * Created by starikovskii.valentine on 12/16/16.
 */

public interface SavedProductItemListener {
    void deleteProduct(ProductModel product, int position);
    void toDetailsFragment(ProductModel product);
}
