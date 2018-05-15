package com.kapil.sample.network.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by kapilsharma on 11/07/17.
 */

@Parcel
public class Inventory {

    @SerializedName("products")
    public List<Product> products;

    @SerializedName("page")
    public int page;

    @SerializedName("page_size")
    public int pageSize;
}
