package com.sprint3r.shoppingmicroserviceclass.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sprint3r.shoppingmicroserviceclass.R;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.view.fragment.ProductConfirmFragment;
import com.sprint3r.shoppingmicroserviceclass.view.fragment.ProductDetailFragment;
import com.sprint3r.shoppingmicroserviceclass.view.fragment.ProductListFragment;

public class MainActivity extends AppCompatActivity {

    private final String STACK_NAME = "product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToProductList();
    }

    private void goToProductList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containProduct, new ProductListFragment())
                .commit();
    }

    public void goToProductDetail(Product product) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containProduct, ProductDetailFragment.newInstance(product))
                .addToBackStack(STACK_NAME)
                .commit();
    }

    public void goToProductConfirm(Product product) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containProduct, ProductConfirmFragment.newInstance(product))
                .addToBackStack(STACK_NAME)
                .commit();
    }
}
