package com.sprint3r.shoppingmicroserviceclass;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    List<String> product;

    public void setProduct(List<String> product) {
        this.product = product;
    }

    public ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProductListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return product == null ? 0 : product.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvProductType;
        ImageView ivProductPhoto;


        public ProductListViewHolder(View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductType = (TextView) itemView.findViewById(R.id.tvProductType);
            ivProductPhoto = (ImageView) itemView.findViewById(R.id.ivProductImage);
        }
    }
}
