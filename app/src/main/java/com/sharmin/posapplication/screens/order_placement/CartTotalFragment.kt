package com.sharmin.posapplication.screens.order_placement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sharmin.posapplication.databinding.FragmentCartTotalBinding
import com.sharmin.posapplication.utils.PriceFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartTotalFragment @Inject constructor() : Fragment() {

    val viewModel: CartTotalViewModel by viewModels()
    private lateinit var binding: FragmentCartTotalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartTotalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        viewModel.cartTotal.observe(viewLifecycleOwner, {
            binding.totalPriceTv.text = PriceFormatter.apply(it)
        })
    }
}