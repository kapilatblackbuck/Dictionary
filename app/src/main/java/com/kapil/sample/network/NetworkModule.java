package com.kapil.sample.network;

import com.kapil.sample.network.api.ProductApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kapilsharma on 11/07/17.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public ProductApi getStackApi() {
        return RetrofitAdapter.get().getRetrofit().create(ProductApi.class);
    }
}
