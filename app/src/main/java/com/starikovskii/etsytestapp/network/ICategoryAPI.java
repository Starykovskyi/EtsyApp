package com.starikovskii.etsytestapp.network;

import com.starikovskii.etsytestapp.model.CategoriesResponseModel;
import com.starikovskii.etsytestapp.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public interface ICategoryAPI {
    @GET(Constants.Network.GET_CATEGORIES_URL)
    Observable<CategoriesResponseModel> getCategories(@Query(Constants.Network.Params.PARAM_API_KEY) String apiKey);
}
