package com.sprint3r.shoppingmicroserviceclass.presenter;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.service.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailPresenter {
    public interface ProductDetailView {
        void render(Product product);
        void error(String msg);
    }

    private ProductDetailView view;
    public void setView(ProductDetailView view) {
        this.view = view;
    }

    public void getProduct(Long id) {
        RetrofitService.getProduct(id)
                .doOnError(Throwable::printStackTrace)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.render(list),
                        t -> view.error(t.getMessage())
                );
    }
}
