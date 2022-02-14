package com.sharmin.posapplication.screens.order_placement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sharmin.posapplication.databinding.FragmentCartListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartListFragment @Inject constructor() : Fragment() {

    val viewModel: CartListViewModel by viewModels()
    private lateinit var binding: FragmentCartListBinding
    private lateinit var cartListAdapter: CartListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setUpListeners()
    }

    private fun setupView() {
        cartListAdapter = CartListAdapter()
        binding.recyclerViewCartItemList.adapter = cartListAdapter
    }

    private fun setUpListeners() {
        viewModel.cartItems.observe(viewLifecycleOwner) {
            cartListAdapter.addHeaderAndSubmitList(it)
        }
    }
}