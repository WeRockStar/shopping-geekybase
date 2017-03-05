package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.service.RetrofitService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductListPresenter {

    public interface ProductListView {
        void render(List<Product> products);
        void error(String msg);
    }

    private ProductListView view;

    public void setView(ProductListView view) {
        this.view = view;
    }

    public void listProduct() {
        RetrofitService.getAllProduct()
                .doOnError(Throwable::printStackTrace)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.render(list),
                        t -> view.error(t.getMessage())
                );
    }
}
