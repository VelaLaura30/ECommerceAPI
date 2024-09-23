package com.example.e_commerceapi.ui.screens.product.rv

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapi.data.api.models.Product
import com.example.e_commerceapi.databinding.ProductViewBinding

class ProductViewHolder(
    private val binding: ProductViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        with(binding) {
            productTitle.text = product.title
            productPrice.text = product.price.toString()
            productDescription.text = product.description
            Glide.with(productImage.context).load(product.image).into(productImage)
        }
    }
}