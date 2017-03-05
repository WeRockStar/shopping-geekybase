package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;

public class ShoppingDetailPresenter {
    public interface ShoppingDetailView {
        void render(Product product);
    }

    private ShoppingDetailView view;
    public void setView(ShoppingDetailView view) {
        this.view = view;
    }

    public void getProduct(Long id) {

    }
}
