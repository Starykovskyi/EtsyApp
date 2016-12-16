package com.starikovskii.etsytestapp.model.dao;

import com.starikovskii.etsytestapp.model.ProductModel;

import java.util.List;

import rx.Observable;

/**
 * Created by FkingAlive on 16.12.2016.
 */

public interface IProductDAO {
    Observable<Long> saveProduct(ProductModel productModel);
    Observable<Void> deleteProduct(ProductModel productModel);
    Observable<List<ProductModel>> getProducts(int offset);

}
