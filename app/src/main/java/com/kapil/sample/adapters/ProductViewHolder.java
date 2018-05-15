package com.kapil.sample.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kapil.sample.Constants;
import com.kapil.sample.MainApplication;
import com.kapil.sample.R;
import com.kapil.sample.network.data.Product;

/**
 * Created by kapilsharma on 11/07/17.
 */

public class ProductViewHolder extends ItemViewHolder<Product> implements View.OnClickListener {
    public TextView title;
    public TextView cta;
    public ImageView image;

    public ProductViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        cta = (TextView) itemView.findViewById(R.id.cta);
        image = (ImageView) itemView.findViewById(R.id.img);
        itemView.setOnClickListener(this);
        cta.setOnClickListener(this);
    }

    public void onBind(Product mProduct) {
        title.setText(mProduct.title);
        Glide.with(image.getContext()).load(Constants.img_url_prefix + mProduct.img.url)
                .placeholder(R.drawable.tz_ic_mountain_placeholder)
                .into(image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cta:
                onCtaClick();
                break;
            default:
                onItemClick();
        }

    }

    private void onItemClick() {
        MainApplication.getInstance().showToast("Detail yet to develop..");
    }

    private void onCtaClick() {
        MainApplication.getInstance().showToast("Product added to Cart....");

    }
}
