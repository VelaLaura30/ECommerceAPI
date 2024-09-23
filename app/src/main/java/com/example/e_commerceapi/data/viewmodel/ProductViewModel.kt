package com.example.e_commerceapi.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapi.data.api.retrofit.RetrofitService
import com.example.e_commerceapi.ui.screens.product.uiState.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel () {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    fun loadProductsByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isProductListLoading = true)

            val products = retrofitApi.getAllProducts()
            val filteredProducts = products.filter { it.category == category }

            _uiState.value = _uiState.value.copy(
                isProductListLoading = false,
                products = filteredProducts
            )
        }
    }



}