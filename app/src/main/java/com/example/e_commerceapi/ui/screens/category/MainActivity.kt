package com.example.e_commerceapi.ui.screens.category

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapi.data.viewmodel.CategoryViewModel
import com.example.e_commerceapi.databinding.ActivityCategoriesBinding
import com.example.e_commerceapi.ui.screens.category.rv.RVCategoryAdapter
import com.example.e_commerceapi.ui.screens.product.ProductActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val categoriesViewModel: CategoryViewModel by viewModels()
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var rvCategoriesAdapter: RVCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
    }

    private fun initRV() {
        rvCategoriesAdapter = RVCategoryAdapter(
            onCategoryClickListener = { categoryName ->
                launchProductsActivity(categoryName)
            },
        )
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvCategoriesAdapter
        }
    }


    private fun launchProductsActivity(categoryName: String) {
        startActivity(
            Intent(
                this,
                ProductActivity::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        "CATEGORY_NAME" to categoryName
                    )
                )
            }
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            categoriesViewModel.uiState.collect { uiState ->
                uiState.categories?.let { categoriesList ->
                    rvCategoriesAdapter.categories = categoriesList
                    rvCategoriesAdapter.notifyDataSetChanged()
                }
                binding.rvCategories.visibility = if (uiState.isLoading) View.INVISIBLE else View.VISIBLE
                binding.pbCategories.visibility = if (uiState.isLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }
}