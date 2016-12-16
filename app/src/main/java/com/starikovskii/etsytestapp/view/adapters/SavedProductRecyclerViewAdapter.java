package com.starikovskii.etsytestapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.starikovskii.etsytestapp.EtsyApplication;
import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.utils.listeners.SavedProductItemListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by starikovskii.valentine on 12/16/16.
 */

public class SavedProductRecyclerViewAdapter extends RecyclerView.Adapter<SavedProductRecyclerViewAdapter.SavedProductViewHolder> {

    private View root;
    private List<ProductModel> products;
    private static SavedProductItemListener itemClickListener;


    public SavedProductRecyclerViewAdapter(SavedProductItemListener listener) {
        itemClickListener = listener;
        products = new ArrayList<>();
    }

    @Override
    public SavedProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_product, parent, false);
        return new SavedProductViewHolder(root, itemClickListener);
    }

    @Override
    public void onBindViewHolder(SavedProductViewHolder holder, int position) {
        holder.bindView(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addProductList(List<ProductModel> products) {
        int oldSize = getItemCount();
        this.products.addAll(products);
        notifyItemRangeInserted(oldSize, products.size());
    }

    public void deleteProduct(int position) {
        this.products.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        this.products.clear();
        notifyDataSetChanged();
    }


    public static class SavedProductViewHolder extends RecyclerView.ViewHolder {
        @Inject
        Context context;
        private SavedProductItemListener listener;
        @BindView(R.id.ivProductImage)
        ImageView ivProductImage;
        @BindView(R.id.tvProductName)
        TextView tvProductName;
        @BindView(R.id.ibtnDelete)
        ImageButton ibtnDelete;
        public SavedProductViewHolder(View itemView, final SavedProductItemListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            EtsyApplication.getMainComponent().inject(this);
            this.listener = listener;
        }

        void bindView(final ProductModel product) {

            tvProductName.setText(product.getTitle());
            Glide
                    .with(context)
                    .load(product.getMainImage().getUrlFullxfull())
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(ivProductImage);
            ivProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.toDetailsFragment(product);
                }
            });
            ibtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deleteProduct(product, getAdapterPosition());
                }
            });

        }
    }


}
