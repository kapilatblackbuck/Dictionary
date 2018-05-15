package com.kapil.sample.network.data;

import com.google.gson.annotations.SerializedName;
import com.kapil.sample.ViewType;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by kapilsharma on 11/07/17.
 */

@Parcel
public class Product {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("desc")
    public String desc;

    public int type = ViewType.PRODUCT;

    @SerializedName("img")
    public Img img;

    @SerializedName("images")
    public List<Image> images;


    @Parcel
    public static class Img {

        @SerializedName("name")
        public String url;

    }

    @Parcel
    public static class Image {

        @SerializedName("name")
        public String url;

    }


}
