package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.service.RetrofitService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductListPresenter {

    public interface ShoppingListView {
        void render(List<Product> products);
        void error(String msg);
    }

    private ShoppingListView view;

    public void setView(ShoppingListView view) {
        this.view = view;
    }

    public void listProduct() {
        RetrofitService.getApi().getAllProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.render(list),
                        t -> view.error(t.getMessage())
                );
    }
}
