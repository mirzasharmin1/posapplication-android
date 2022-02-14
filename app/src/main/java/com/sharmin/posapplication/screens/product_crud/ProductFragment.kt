package com.sharmin.posapplication.screens.product_crud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sharmin.posapplication.databinding.FragmentProductListBinding
import com.sharmin.posapplication.db.models.Product
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment @Inject constructor(val productClickListener: (Product) -> Unit) : Fragment() {
    val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: FragmentProductListBinding
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setupListeners()
    }

    private fun setupView() {
        productListAdapter = ProductListAdapter(productClickListener)
        binding.recyclerViewProductList.adapter = productListAdapter
    }

    private fun setupListeners() {
        viewModel.products.observe(viewLifecycleOwner) {
            productListAdapter.submitList(it)
        }
    }
}