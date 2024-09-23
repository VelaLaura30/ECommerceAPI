package com.example.e_commerceapi.ui.screens.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapi.R
import com.example.e_commerceapi.data.viewmodel.ProductViewModel
import com.example.e_commerceapi.databinding.ActivityProductsBinding
import com.example.e_commerceapi.ui.screens.product.rv.RVProductAdapter
import kotlinx.coroutines.launch

class ProductActivity : AppCompatActivity() {

    private val productsViewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityProductsBinding
    private lateinit var rvProductsAdapter: RVProductAdapter
    private lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryName = intent.extras?.getString("CATEGORY_NAME") ?: return
        setupCategoryInfo()
        productsViewModel.loadProductsByCategory(categoryName)
        initRV()
        initUiStateLifecycle(categoryName)
    }

    private fun setupCategoryInfo() {
        val categoryTextView: TextView = findViewById(R.id.tv_category_name)
        categoryTextView.text = categoryName
    }

    private fun initRV() {
        rvProductsAdapter = RVProductAdapter()
        binding.rvCategoryProducts.apply {
            layoutManager = LinearLayoutManager(this@ProductActivity)
            adapter = rvProductsAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle(categoryName: String) {
        lifecycleScope.launch {
            productsViewModel.uiState.collect { uiState ->
                uiState.products?.let { productList ->
                    rvProductsAdapter.products = productList
                    rvProductsAdapter.notifyDataSetChanged()
                }
                binding.rvCategoryProducts.visibility = if (uiState.isProductListLoading) View.INVISIBLE else View.VISIBLE
                binding.pbProducts.visibility = if (uiState.isProductListLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }
}
