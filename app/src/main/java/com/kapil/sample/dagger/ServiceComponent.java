package com.kapil.sample.dagger;


import com.kapil.sample.network.NetworkModule;
import com.kapil.sample.network.api.ProductApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kapilsharma on 11/07/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface ServiceComponent {
    ProductApi getProductApi();
}
