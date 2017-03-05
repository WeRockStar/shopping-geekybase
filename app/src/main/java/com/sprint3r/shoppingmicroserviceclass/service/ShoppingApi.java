package com.sprint3r.shoppingmicroserviceclass.service;


import com.sprint3r.shoppingmicroserviceclass.domain.Order;
import com.sprint3r.shoppingmicroserviceclass.domain.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ShoppingApi {
    @GET("/products")
    Observable<List<Product>> getAllProduct();

    @GET("/product/{id}")
    Observable<Product> getProduct(@Path("id") Long id);

    @POST("/order")
    Observable<String> order(@Body Order order);
}
