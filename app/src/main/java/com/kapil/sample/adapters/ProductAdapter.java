package com.kapil.sample.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kapil.sample.R;
import com.kapil.sample.ViewType;
import com.kapil.sample.network.data.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kapilsharma on 11/07/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final Product loader;
    private List<Product> data;
    private Activity mActivity;

    public ProductAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        data = new ArrayList<>();
        loader = new Product();
        loader.type = ViewType.LOADER;
        data.add(loader);

    }

    public synchronized void addData(List<Product> data) {
        int oldSize = this.data.size();
        this.data.remove(loader);
        this.data.addAll(data);
        this.data.add(loader);
        this.notifyItemRangeInserted(oldSize, data.size());
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ItemViewHolder itemViewHolder = null;
        switch (viewType) {
            case ViewType.LOADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.loader_view, parent, false);
                itemViewHolder = new LoaderViewHolder(view);
                break;
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_item_view, parent, false);
                itemViewHolder = new ProductViewHolder(view);
        }
        return itemViewHolder;

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }
}
