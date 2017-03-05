package com.sprint3r.shoppingmicroserviceclass.view.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sprint3r.shoppingmicroserviceclass.R;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;

public class ProductConfirmFragment extends Fragment {

    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";

    Button btnConfirm;

    public static ProductConfirmFragment newInstance(Product products) {
        Bundle args = new Bundle();
        ProductConfirmFragment fragment = new ProductConfirmFragment();
        args.putParcelable(EXTRA_PRODUCT, products);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductConfirmFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_confirm, container, false);
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("คุณยืนยันการทำรายการหรือไม่?");

        btnConfirm.setOnClickListener(v -> {
            builder.setPositiveButton("ตกลง", (dialog, which) -> {

            });
            builder.setNegativeButton("ยกเลิก", (dialog, which) -> {
            });

            builder.show();
        });

        return view;
    }

}
