package com.sprint3r.shoppingmicroserviceclass;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    public void goToProductDetail(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containProduct, fragment)
                .addToBackStack(STACK_NAME)
                .commit();
    }
}
