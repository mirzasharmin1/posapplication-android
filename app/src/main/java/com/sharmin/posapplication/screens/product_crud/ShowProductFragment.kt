package com.sharmin.posapplication.screens.product_crud

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.FragmentShowProductBinding
import com.sharmin.posapplication.db.models.Product
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowProductFragment @Inject constructor(val product: Product) : Fragment() {

    val viewModel: ShowProductViewModel by viewModels()
    private lateinit var binding: FragmentShowProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        loadImg(requireContext(), product.productImg)
        binding.productTitleEt.setText(product.name)
        binding.productTypeEt.setText(product.productType.name)
        binding.productPriceEt.setText(product.price.toString())
        binding.productUnitsAvailableEt.setText(product.unitsAvailable.toString())
    }

    private fun loadImg(context: Context, imgUrl: String) {
        Glide.with(context)
            .load(imgUrl)
            .error(R.drawable.broken)
            .into(binding.productImageIv)
    }
}