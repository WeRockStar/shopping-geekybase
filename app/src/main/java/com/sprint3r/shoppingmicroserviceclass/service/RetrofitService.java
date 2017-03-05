package com.sprint3r.shoppingmicroserviceclass.service;

import com.sprint3r.shoppingmicroserviceclass.domain.Order;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;
import com.sprint3r.shoppingmicroserviceclass.domain.Tracking;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static String BASE_URL = "http://10.0.1.24:8081";
    private static Retrofit retrofit;
    private static ShoppingApi api;

    public static Retrofit getRetrofitService() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
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

    public static Observable<List<Product>> getAllProduct() {
        return getApi().getAllProduct();
    }

    public static Observable<Product> getProduct(Long id) {
        return getApi().getProduct(id);
    }

    public static Observable<Tracking> order(Product product, String firstName, String lastName, String address, String description) {
        Order order = new Order();
        order.setPrice(product.getPrice().floatValue());
        order.setFristName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setDesc(description);
        return getApi().order(order);
    }

    public static void main(String[] args) {
        ShoppingApi api = getApi();
        api.getAllProduct()
                .subscribe(
                        list -> {
                            System.out.println(list.size() + list.get(0).getDescription());
                        },
                        Throwable::printStackTrace,
                        () -> System.out.println("com")
                        );
    }
}
