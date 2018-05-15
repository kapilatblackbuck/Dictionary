package com.kapil.sample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kapilsharma on 11/07/17.
 */

public abstract class ItemViewHolder<Product> extends RecyclerView.ViewHolder {

    Product mProduct;

    public ItemViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(Product mProduct) {
        this.mProduct = mProduct;
    }
}
