package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.domain.Tracking;
import com.sprint3r.shoppingmicroserviceclass.service.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter {
    public interface OrderView {
        void render(Tracking trackingNumber);
        void error(String msg);
    }

    private OrderView view;
    public void setView(OrderView view) {
        this.view = view;
    }

    public void order(Product product, String firstName, String lastName, String address, String description) {
        RetrofitService.order(product, firstName, lastName, address, description)
                .doOnError(Throwable::printStackTrace)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        tracking -> view.render(tracking),
                        t -> view.error(t.getMessage())
                );
    }
}
