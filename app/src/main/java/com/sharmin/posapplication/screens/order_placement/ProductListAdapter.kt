package com.sharmin.posapplication.screens.order_placement

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.ItemOrderProductListBinding
import com.sharmin.posapplication.db.models.Product

class ProductListAdapter(private val addToCartCallback: (Product) -> Unit, private val removeFromCartCallback: (Product) -> Unit)
    : ListAdapter<Product, RecyclerView.ViewHolder>(ProductListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position) as Product
        (holder as ViewHolder).bind(product, addToCartCallback, removeFromCartCallback)
    }

    class ViewHolder(private val binding: ItemOrderProductListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, addToCartCallback: (Product) -> Unit, removeFromCartCallback: (Product) -> Unit) {
            val context = binding.productImageIv.context
            binding.productTitleTv.text = product.name
            loadImg(context, product.productImg)
            binding.addItemBtn.setOnClickListener { addToCartCallback(product) }
            binding.removeItemBtn.setOnClickListener { removeFromCartCallback(product) }
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
                val binding = ItemOrderProductListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ProductListDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}