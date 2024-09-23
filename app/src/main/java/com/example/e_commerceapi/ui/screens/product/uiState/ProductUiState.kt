package com.example.e_commerceapi.ui.screens.product.uiState

import com.example.e_commerceapi.data.api.models.Category
import com.example.e_commerceapi.data.api.models.Product

data class ProductUiState (

    val category: Category? = null,
    val products: List<Product> = emptyList(),
    val isCategoryLoading: Boolean = true,
    val isProductListLoading: Boolean = true,

    )