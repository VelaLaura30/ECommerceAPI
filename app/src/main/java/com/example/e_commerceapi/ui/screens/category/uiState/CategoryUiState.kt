package com.example.e_commerceapi.ui.screens.category.uiState

import com.example.e_commerceapi.data.api.models.Category

data class CategoryUiState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = true
)
