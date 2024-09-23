package com.example.e_commerceapi.ui.screens.category.rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapi.data.api.models.Category
import com.example.e_commerceapi.databinding.CategoryViewBinding

class CategoryViewHolder(
    private val binding: CategoryViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, onClick: (String) -> Unit) {
        binding.tvCategoryName.text = category.name
        binding.ivCategoryPicture.setImageResource(category.imageResId)

        binding.btnProductos.setOnClickListener {
            onClick(category.name)
        }
    }
}

