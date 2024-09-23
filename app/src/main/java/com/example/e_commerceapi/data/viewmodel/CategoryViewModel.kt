package com.example.e_commerceapi.data.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapi.R
import com.example.e_commerceapi.data.api.models.Category
import com.example.e_commerceapi.data.api.retrofit.RetrofitService
import com.example.e_commerceapi.ui.screens.category.uiState.CategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CategoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    init {
        viewModelScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        val categories = retrofitApi.getCategories()

        val categoriesWithImages = categories.map { categoryName ->
            val imageResId = when (categoryName) {
                "electronics" -> R.drawable.electronics
                "jewelery" -> R.drawable.jewelery
                "men's clothing" -> R.drawable.mens_clothing
                "women's clothing" -> R.drawable.womens_clothing
                else -> R.drawable.default_image
            }
            Category(categoryName, imageResId)
        }

        _uiState.value = _uiState.value.copy(
            isLoading = false,
            categories = categoriesWithImages
        )
    }
}




