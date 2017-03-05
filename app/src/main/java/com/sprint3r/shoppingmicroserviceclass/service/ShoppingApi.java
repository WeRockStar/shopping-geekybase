package com.sprint3r.shoppingmicroserviceclass.service;


import com.sprint3r.shoppingmicroserviceclass.domain.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ShoppingApi {
    @GET("/products")
    Observable<List<Product>> getAllProduct();

    @GET("/product/{id}")
    Observable<Product> getProduct(Long id);
}
