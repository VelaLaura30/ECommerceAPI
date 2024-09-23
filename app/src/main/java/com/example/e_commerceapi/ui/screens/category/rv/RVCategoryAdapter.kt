package com.example.e_commerceapi.ui.screens.category.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapi.data.api.models.Category
import com.example.e_commerceapi.databinding.CategoryViewBinding

class RVCategoryAdapter(

    var categories: List<Category> = emptyList(),
    val onCategoryClickListener: (String) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position], onCategoryClickListener)
    }

    override fun getItemCount(): Int = categories.size
}