package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.service.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter {
    public interface OrderView {
        void render(String trackingNumber);
        void error(String msg);
    }

    private OrderView view;
    public void setView(OrderView view) {
        this.view = view;
    }

    public void order(Long productId, Double price, String customerName, String description) {
        RetrofitService.order(productId, price, customerName, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.render(list),
                        t -> view.error(t.getMessage())
                );
    }
}
