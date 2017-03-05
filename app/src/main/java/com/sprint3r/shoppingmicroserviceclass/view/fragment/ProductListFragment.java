package com.sprint3r.shoppingmicroserviceclass.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sprint3r.shoppingmicroserviceclass.R;
import com.sprint3r.shoppingmicroserviceclass.adapter.ProductListAdapter;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.presenter.ProductListPresenter;
import com.sprint3r.shoppingmicroserviceclass.view.activity.MainActivity;

import java.util.List;


public class ProductListFragment extends Fragment implements ProductListAdapter.OnClickProduct, ProductListPresenter.ProductListView {


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProductListAdapter adapter;

    ProductListPresenter presenter;

    public ProductListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        presenter = new ProductListPresenter();
        presenter.setView(this);
        presenter.listProduct();

        initialView(view);
        setUpRecyclerView();


        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.listProduct();
        });
        return view;
    }

    private void initialView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
    }

    private void setUpRecyclerView() {
        adapter = new ProductListAdapter(getContext());
        adapter.setListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClickProductItem(Product product) {
        ((MainActivity) getActivity()).goToProductDetail(product);
    }

    @Override
    public void render(List<Product> products) {
        adapter.setProduct(products);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void error(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}
