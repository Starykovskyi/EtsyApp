package com.starikovskii.etsytestapp.model.dao;

import com.activeandroid.query.Select;
import com.starikovskii.etsytestapp.model.ProductImageModel;
import com.starikovskii.etsytestapp.model.ProductModel;

import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by FkingAlive on 16.12.2016.
 */

public class ProductDAO implements IProductDAO {
    @Override
    public Observable<Long> saveProduct(final ProductModel productModel) {

        Observable<Long> observable = Observable.defer(new Func0<Observable<Long>>() {
            @Override public Observable<Long> call() {
                productModel.getMainImage().save();
                return Observable.just( productModel.save());
            }
        });
        return observable;

    }

    @Override
    public Observable<Void> deleteProduct(final ProductModel productModel) {
        Observable<Void> observable = Observable.defer(new Func0<Observable<Void>>() {
            @Override public Observable<Void> call() {
                productModel.delete();
                return Observable.just(null);
           }
        });
        return observable;

    }

    @Override
    public Observable<List<ProductModel>> getProducts(final int offset) {
        Observable<List<ProductModel>> observable = Observable.defer(new Func0<Observable<List<ProductModel>>>() {
            @Override public Observable<List<ProductModel>> call() {
                List<ProductModel> list = new Select()
                        .from(ProductModel.class)
                        .limit(25)
                        .offset(offset)
                        .execute();

                return Observable.just(list);
            }
        });
        return observable;
    }
}
