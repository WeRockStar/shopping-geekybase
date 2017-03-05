package com.sprint3r.shoppingmicroserviceclass.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sprint3r.shoppingmicroserviceclass.R;

public class ProductConfirmFragment extends Fragment {

    public ProductConfirmFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_confirm, container, false);
        return view;
    }

}
