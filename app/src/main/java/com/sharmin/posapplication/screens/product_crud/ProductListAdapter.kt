package com.sharmin.posapplication.screens.product_crud

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.ItemProductProductListBinding
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.screens.order_placement.ProductListDiffCallback

class ProductListAdapter(val productClickListener: (Product) -> Unit) : ListAdapter<Product, RecyclerView.ViewHolder>(ProductListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position) as Product
        (holder as ViewHolder).bind(product, productClickListener)
    }

    class ViewHolder(private val binding: ItemProductProductListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, productClickListener: (Product) -> Unit) {
            val context = binding.productImageIv.context
            binding.productTitleTv.text = product.name
            binding.root.setOnClickListener {
                productClickListener(product)
            }
            loadImg(context, product.productImg)
        }

        private fun loadImg(context: Context, imgUrl: String) {
            Glide.with(context)
                .load(imgUrl)
                .error(R.drawable.broken)
                .into(binding.productImageIv)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductProductListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
