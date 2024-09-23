package com.example.e_commerceapi.data.api.retrofit

import com.example.e_commerceapi.data.api.models.Category
import com.example.e_commerceapi.data.api.models.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products")
    suspend fun getAllProducts(): List<Product>




}