package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.service.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.description;

public class OrderPresenter {
    public interface OrderView {
        void render(String trackingNumber);
        void error(String msg);
    }

    private OrderView view;
    public void setView(OrderView view) {
        this.view = view;
    }

    public void order(Product product, String fristName, String lastName, String address, String description) {
        RetrofitService.order(product, fristName, lastName, address, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.render(list),
                        t -> view.error(t.getMessage())
                );
    }
}
