package com.sprint3r.shoppingmicroserviceclass.view.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sprint3r.shoppingmicroserviceclass.R;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<Product> productList;
    private Context context;
    private OnClickProduct listener;

    public void setListener(OnClickProduct listener) {
        this.listener = listener;
    }

    public ProductListAdapter(Context context) {
        this.context = context;
    }

    public void setProduct(List<Product> product) {
        this.productList = product;
    }

    public ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.tvProductName.setText(product.getName());
        holder.tvProductType.setText(product.getType());
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()));

        Glide.with(context)
                .load(product.getImageUrl())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivProductPhoto);

        holder.productBox.setOnClickListener(v -> listener.onClickProductItem(product));
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvProductType;
        ImageView ivProductPhoto;
        CardView productBox;


        public ProductListViewHolder(View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductType = (TextView) itemView.findViewById(R.id.tvProductType);
            ivProductPhoto = (ImageView) itemView.findViewById(R.id.ivProductImage);
            productBox = (CardView) itemView.findViewById(R.id.product_box);
        }
    }

    public interface OnClickProduct {
        void onClickProductItem(Product product);
    }
}
