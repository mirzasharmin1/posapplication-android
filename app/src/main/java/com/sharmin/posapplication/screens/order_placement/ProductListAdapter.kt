package com.sharmin.posapplication.screens.order_placement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sharmin.posapplication.databinding.ItemOrderProductListBinding
import com.sharmin.posapplication.db.models.Product

class ProductListAdapter : ListAdapter<Product, RecyclerView.ViewHolder>(ProductListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position) as Product
        (holder as ViewHolder).bind(product)
    }

    class ViewHolder(val binding: ItemOrderProductListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productTitleTv.text = product.name

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