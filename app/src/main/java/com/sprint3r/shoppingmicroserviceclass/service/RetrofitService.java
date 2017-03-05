package com.sprint3r.shoppingmicroserviceclass.service;

import com.sprint3r.shoppingmicroserviceclass.domain.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static String BASE_URL = "http://localhost:8080";
    private static Retrofit retrofit;
    private static ShoppingApi api;

    public static Retrofit getRetrofitService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ShoppingApi getApi() {
        if (api == null) {
            api = getRetrofitService().create(ShoppingApi.class);
        }
        return api;
    }

    public Observable<List<Product>> getAllProduct() {
        return getApi().getAllProduct();
    }

    public Observable<Product> getProduct(Long id) {
        return getApi().getProduct(id);
    }

    public static void main(String[] args) {
        ShoppingApi api = getApi();
        api.getAllProduct()
                .subscribe(
                        list -> {
                            System.out.println(list.size());
                        },
                        Throwable::printStackTrace,
                        () -> System.out.println("com")
                        );
    }
}