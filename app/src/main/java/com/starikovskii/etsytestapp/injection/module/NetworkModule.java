package com.starikovskii.etsytestapp.injection.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starikovskii.etsytestapp.utils.Constants;
import com.starikovskii.etsytestapp.network.ICategoryAPI;
import com.starikovskii.etsytestapp.network.IProductAPI;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FkingAlive on 12.12.2016.
 */

@Module
public class NetworkModule {
    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(16000, TimeUnit.MILLISECONDS);
        clientBuilder.readTimeout(16000, TimeUnit.MILLISECONDS);
        clientBuilder.writeTimeout(16000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = clientBuilder.addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Network.ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    ICategoryAPI provideICategory (Retrofit retrofit) {
        return retrofit.create(ICategoryAPI.class);
    }

    @Provides
    IProductAPI provideIProductAPI(Retrofit retrofit){
        return retrofit.create(IProductAPI.class);
    }
}
