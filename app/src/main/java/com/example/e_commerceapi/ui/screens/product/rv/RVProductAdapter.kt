package com.example.e_commerceapi.ui.screens.product.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapi.data.api.models.Product
import com.example.e_commerceapi.databinding.ProductViewBinding
import com.example.e_commerceapi.ui.screens.product.ProductActivity

class RVProductAdapter: RecyclerView.Adapter<ProductViewHolder>(){

    var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}