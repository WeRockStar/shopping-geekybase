package com.sprint3r.shoppingmicroserviceclass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<String> product;
    private Context context;

    public ProductListAdapter(Context context) {
        this.context = context;
    }

    public void setProduct(List<String> product) {
        this.product = product;
    }

    public ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListViewHolder holder, int position) {

        String p = product.get(position);

        holder.tvProductName.setText(p);
        holder.tvProductType.setText("");
        holder.tvProductPrice.setText("");

        Glide.with(context)
                .load("")
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivProductPhoto);
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
