package com.starikovskii.etsytestapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.screens.search.ISearchFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FkingAlive on 14.12.2016.
 */

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {

    private View root;
    private List<ProductModel> products;
    private static ProductItemClickListener itemClickListener;


    public ProductRecyclerViewAdapter(ProductItemClickListener listener) {
        itemClickListener = listener;
        products = new ArrayList<>();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(root, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bindView(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addProductList(List<ProductModel> products){
        int oldSize = getItemCount();
        this.products.addAll(products);
        notifyItemRangeInserted(oldSize, products.size());
    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        @Inject
        Context context;
        private View root;

        private ProductItemClickListener listener;
        @BindView(R.id.ivProductImage)
        ImageView ivProductImage;
        @BindView(R.id.tvProductName)
        TextView tvProductName;

        public ProductViewHolder(View itemView, final ProductItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            EtsyApplication.getAppComponent().inject(this);
            this.root = itemView;
            this.listener = listener;
        }

        void bindView(final ProductModel product) {

            tvProductName.setText(product.getTitle());
            ivProductImage.getWidth();
            Glide
                    .with(context)
                    .load(product.getMainImage().getUrlFullxfull())
                    .fitCenter()
                    .diskCacheStrategy( DiskCacheStrategy.RESULT )
                    .into(ivProductImage);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(product);
                }
            });
        }
    }

    public interface ProductItemClickListener {
        void onClick(ProductModel product);
    }

}
