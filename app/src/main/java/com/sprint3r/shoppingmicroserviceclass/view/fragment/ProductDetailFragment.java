package com.sprint3r.shoppingmicroserviceclass.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sprint3r.shoppingmicroserviceclass.R;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;


public class ProductDetailFragment extends Fragment {

    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";

    TextView tvProductName;
    TextView tvProductPrice;
    TextView tvProductType;
    ImageView ivProductPhoto;
    TextView tvProductDescription;


    public static ProductDetailFragment newInstance(Product products) {
        Bundle args = new Bundle();
        ProductDetailFragment fragment = new ProductDetailFragment();
        args.putParcelable(EXTRA_PRODUCT, products);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        initialView(view);
        displayProductDescription();

        return view;
    }

    private void displayProductDescription() {
        tvProductName.setText(getProduct().getName());
        tvProductPrice.setText(getProduct().getPrice() + "");
        tvProductType.setText(getProduct().getType());
        tvProductDescription.setText(getProduct().getDescription());

        Glide.with(getContext())
                .load(getProduct().getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(ivProductPhoto);
    }

    private void initialView(View view) {
        tvProductName = (TextView) view.findViewById(R.id.tvProductName);
        tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
        tvProductType = (TextView) view.findViewById(R.id.tvProductType);
        ivProductPhoto = (ImageView) view.findViewById(R.id.ivProductImage);
        tvProductDescription = (TextView) view.findViewById(R.id.tvProductDescription);
    }

    public Product getProduct() {
        return getArguments().getParcelable(EXTRA_PRODUCT);
    }
}
