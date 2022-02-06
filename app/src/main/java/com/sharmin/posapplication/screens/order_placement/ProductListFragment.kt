package com.sharmin.posapplication.screens.order_placement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sharmin.posapplication.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment @Inject constructor() : Fragment() {

    val viewModel: ProductListViewModel by viewModels()
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
        setUpListeners()
    }

    private fun setupView() {
        productListAdapter = ProductListAdapter(viewModel::addProductToCart, viewModel::removeProductFromCart)
        binding.recyclerViewProductList.adapter = productListAdapter
    }

    private fun setUpListeners() {
        viewModel.products.observe(viewLifecycleOwner, {
            productListAdapter.submitList(it)
        })
    }
}