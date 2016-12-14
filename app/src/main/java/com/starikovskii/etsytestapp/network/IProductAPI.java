package com.starikovskii.etsytestapp.network;

import com.starikovskii.etsytestapp.model.ProductResponseModel;
import com.starikovskii.etsytestapp.utils.Constants;

import retrofit2.http.Query;
import rx.Observable;
import retrofit2.http.GET;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public interface IProductAPI {
    @GET(Constants.Network.GET_PRODUCTS_URL)
    Observable<ProductResponseModel> getProducts(@Query(Constants.Network.Params.PARAM_API_KEY) String apiKey,
                                                 @Query(Constants.Network.Params.PARAM_CATEGORY) String category,
                                                 @Query(Constants.Network.Params.PARAM_KEYWORDS) String keyword,
                                                 @Query(Constants.Network.Params.PARAM_INCLUDES) String includes,
                                                 @Query(Constants.Network.Params.PARAM_OFFSET) int offset);
}
