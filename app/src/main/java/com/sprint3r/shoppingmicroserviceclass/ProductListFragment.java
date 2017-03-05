package com.sprint3r.shoppingmicroserviceclass;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment {


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProductListAdapter adapter;

    public ProductListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        initialView(view);
        setUpRecyclerView();

        swipeRefreshLayout.setOnRefreshListener(() -> {

        });
        return view;
    }

    private void initialView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
    }

    private void setUpRecyclerView() {
        List<String> product = new ArrayList<>();
        product.add("Hello");
        product.add("Hello");
        product.add("Hello");
        product.add("Hello");
        product.add("Hello");
        product.add("Hello");
        product.add("Hello");
        adapter = new ProductListAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        adapter.setProduct(product);
        adapter.notifyDataSetChanged();
    }
}
