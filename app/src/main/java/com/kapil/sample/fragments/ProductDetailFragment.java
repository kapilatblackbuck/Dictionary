package com.kapil.sample.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kapil.sample.Logger;
import com.kapil.sample.MainApplication;
import com.kapil.sample.R;
import com.kapil.sample.adapters.ItemOffsetDecoration;
import com.kapil.sample.adapters.ProductAdapter;
import com.kapil.sample.network.data.Inventory;
import com.kapil.sample.network.data.Product;

import org.parceler.Parcels;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ProductDetailFragment extends Fragment {

    private static final String TAG = ProductDetailFragment.class.getSimpleName();
    private static final String KEY_PRODUCT_DATA = "product_data";
    private Product product;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public static ProductDetailFragment newInstance(Product mProduct) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PRODUCT_DATA, Parcels.wrap(mProduct));
        return productDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = Parcels.unwrap(getArguments().getParcelable(KEY_PRODUCT_DATA));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
    }

}
