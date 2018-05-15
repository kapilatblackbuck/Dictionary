package com.kapil.sample.network.api;


import com.kapil.sample.network.data.Inventory;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kapilsharma on 11/07/17.
 */
public interface ProductApi {

    @GET("/v1.5.8/catalog/search")
    Observable<Inventory> getProducts(@Query("page") int page, @Query("pageSize") int pageSize);
}
