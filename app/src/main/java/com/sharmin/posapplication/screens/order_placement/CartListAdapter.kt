package com.sharmin.posapplication.screens.order_placement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.ItemOrderCartListBinding
import com.sharmin.posapplication.domain.models.CartItem
import com.sharmin.posapplication.utils.PriceFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val ITEM_VIEW_TYPE_HEADER = 0
const val ITEM_VIEW_TYPE_ITEM = 1

class CartListAdapter : ListAdapter<CartListItem, RecyclerView.ViewHolder>(CartListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<CartItem>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(CartListItem.Header)
                else -> listOf(CartListItem.Header) + list.map { CartListItem.Data(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val cartItem = getItem(position) as CartListItem.Data
                holder.bind(cartItem.cartItem, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CartListItem.Header -> ITEM_VIEW_TYPE_HEADER
            is CartListItem.Data -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ViewHolder(val binding: ItemOrderCartListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem, position: Int) {
            binding.cartItemNumberTv.text = position.toString()
            binding.cartItemNameTv.text = item.product.name
            binding.cartItemQuantityTv.text = item.quantity.toString()
            binding.cartItemUnitPriceTv.text = PriceFormatter.apply(item.product.price)
            binding.cartItemTotalPriceTv.text = PriceFormatter.apply(item.totalPrice)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOrderCartListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_order_cart_list_header, parent, false)
                return TextViewHolder(view)
            }
        }
    }
}

class CartListDiffCallback : DiffUtil.ItemCallback<CartListItem>() {
    override fun areItemsTheSame(oldItem: CartListItem, newItem: CartListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartListItem, newItem: CartListItem): Boolean {
        return oldItem == newItem
    }
}

sealed class CartListItem {
    data class Data(val cartItem: CartItem): CartListItem() {
        override val id = cartItem.product.id
    }

    object Header: CartListItem() {
        override val id = Int.MIN_VALUE
    }

    abstract val id: Int
}