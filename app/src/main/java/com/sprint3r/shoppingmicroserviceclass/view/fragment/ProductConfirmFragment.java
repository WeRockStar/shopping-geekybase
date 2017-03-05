package com.sprint3r.shoppingmicroserviceclass.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sprint3r.shoppingmicroserviceclass.R;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.presenter.OrderPresenter;

public class ProductConfirmFragment extends Fragment implements OrderPresenter.OrderView {

    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";

    OrderPresenter presenter;

    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    Button btnConfirm;
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtDescription;

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

        presenter = new OrderPresenter();
        presenter.setView(this);

        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        edtFirstName = (EditText) view.findViewById(R.id.edtFirstName);
        edtLastName = (EditText) view.findViewById(R.id.edtLastName);
        edtDescription = (EditText) view.findViewById(R.id.edtDescription);

        builder.setMessage("คุณยืนยันการทำรายการหรือไม่?");
        btnConfirm.setOnClickListener(v -> {
            builder.setPositiveButton("ตกลง", (dialog, which) -> {
                // presenter.order(getProduct());
            });
            builder.setNegativeButton("ยกเลิก", (dialog, which) -> {
            });

            builder.show();
        });

        return view;
    }

    @Override
    public void render(String trackingNumber) {
        builder.setMessage("Tracking number : " + trackingNumber);
        builder.show();
    }

    public Product getProduct() {
        return getArguments().getParcelable(EXTRA_PRODUCT);
    }

    @Override
    public void error(String msg) {

    }
}
