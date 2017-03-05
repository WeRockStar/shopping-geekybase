package com.sprint3r.shoppingmicroserviceclass.view.fragment;


import android.app.ProgressDialog;
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
import com.sprint3r.shoppingmicroserviceclass.domain.Tracking;
import com.sprint3r.shoppingmicroserviceclass.presenter.OrderPresenter;

public class ProductConfirmFragment extends Fragment implements OrderPresenter.OrderView {

    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";

    OrderPresenter presenter;

    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    Button btnConfirm;
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtDescription;
    EditText edtAddress;

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

        progressDialog = new ProgressDialog(getContext());
        builder = new AlertDialog.Builder(getContext());
        presenter = new OrderPresenter();
        presenter.setView(this);

        initialView(view);
        confirmOrder();
        return view;
    }

    private void confirmOrder() {

        progressDialog.setMessage("Loading..");
        btnConfirm.setOnClickListener(v -> {
            builder.setMessage("คุณยืนยันการทำรายการหรือไม่?");
            builder.setPositiveButton("ตกลง", (dialog, which) -> {
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String address = edtAddress.getText().toString();
                String description = edtDescription.getText().toString();
                progressDialog.show();
                presenter.order(getProduct(), firstName, lastName,
                        address, description);
            });
            builder.setNegativeButton("ยกเลิก", (dialog, which) -> {
            });

            builder.show();
        });
    }

    private void initialView(View view) {
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        edtFirstName = (EditText) view.findViewById(R.id.edtFirstName);
        edtLastName = (EditText) view.findViewById(R.id.edtLastName);
        edtDescription = (EditText) view.findViewById(R.id.edtDescription);
        edtAddress = (EditText) view.findViewById(R.id.edtAddress);
    }

    @Override
    public void render(Tracking trackingNumber) {
        progressDialog.dismiss();
        builder.setMessage("Tracking number : " + trackingNumber.getCode() + "\n" + trackingNumber.getDesc());
        builder.setPositiveButton("ตกลง", (dialog, which) -> {

        });
        builder.show();
    }

    public Product getProduct() {
        return getArguments().getParcelable(EXTRA_PRODUCT);
    }

    @Override
    public void error(String msg) {
        builder.setMessage("Error นะจ๊ะ : " + msg);
        builder.setPositiveButton("ตกลง", (dialog, which) -> {

        });
        builder.show();
        progressDialog.dismiss();
    }
}
