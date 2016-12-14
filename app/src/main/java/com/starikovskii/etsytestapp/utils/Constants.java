package com.starikovskii.etsytestapp.utils;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public class Constants {

    public static class Network {
        public static final String API_KEY = "l6pdqjuf7hdf97h1yvzadfce";
        public static final String MAIN_IMAGE_INCLUDES = "MainImage";
        public static final String ENDPOINT = "https://openapi.etsy.com/";
        public static final String GET_CATEGORIES_URL = "v2/taxonomy/categories";
        public static final String GET_PRODUCTS_URL = "v2/listings/active";

        public static class Params {
            public static final String PARAM_API_KEY = "api_key";
            public static final String PARAM_CATEGORY = "category";
            public static final String PARAM_KEYWORDS = "keywords";
            public static final String PARAM_INCLUDES = "includes";
            public static final String PARAM_OFFSET = "offset";
        }
    }
}
