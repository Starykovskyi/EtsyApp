package com.starikovskii.etsytestapp.injection.module;

import com.starikovskii.etsytestapp.model.dao.IProductDAO;
import com.starikovskii.etsytestapp.model.dao.ProductDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by FkingAlive on 16.12.2016.
 */
@Module
public class DAOModule {
    @Singleton
    @Provides
    IProductDAO provideProductDAO(){
        return new ProductDAO();
    }
}
