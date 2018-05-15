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

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ProductsFragment extends Fragment {

    private static final String TAG = ProductsFragment.class.getSimpleName();


    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProductAdapter productAdapter;
    private Observable<Void> pageDetector;
    private Subscription downPageSubscriber;
    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 3; // The minimum amount of items to have below your current scroll position before loading more.
    private static final int PAGE_SIZE = 20;
    private int latestPage = 0;


    public ProductsFragment() {
        // Required empty public constructor
    }


    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.feed_view);
        linearLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        productAdapter = new ProductAdapter(getActivity());
        recyclerView.setAdapter(productAdapter);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        pageDetector = Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (dy > 10) {
                            subscriber.onNext(null);
                        }

                    }
                });
            }
        }).debounce(200, TimeUnit.MILLISECONDS);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadProducts();
        subscribeInfiniteBottomScroll();
    }

    void loadProducts() {
        Subscription subs = MainApplication.getInstance().component().getProductApi().getProducts(latestPage + 1, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Inventory>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.log(TAG, "onError: " + e);
                        //showError(true);
                    }

                    @Override
                    public void onNext(Inventory inventory) {
                        Logger.log(TAG, "onNext: " + inventory.products.size());
                        if (inventory != null) {
                            latestPage = inventory.page;
                            productAdapter.addData(inventory.products);
                        }


                    }
                });
        compositeSubscription.add(subs);
    }

    private void subscribeInfiniteBottomScroll() {
        downPageSubscriber = pageDetector.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(returnValue -> fetchListenBottomScroll());
        compositeSubscription.add(downPageSubscriber);
    }

    private void fetchListenBottomScroll() {

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {

            if (totalItemCount >= previousTotal + 1) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            loading = true;
            //newsListAdapter.addLoader();
            loadProducts();

        }
    }


}
