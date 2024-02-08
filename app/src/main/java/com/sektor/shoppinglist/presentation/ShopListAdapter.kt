package com.sektor.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sektor.shoppinglist.R
import com.sektor.shoppinglist.databinding.ItemShopDisabledBinding
import com.sektor.shoppinglist.databinding.ItemShopEnabledBinding
import com.sektor.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var shopItemList = listOf<ShopItem>()
        set(value) {
            // Create own Diff Callback to avoid using notifyDataSetChanged()
//            val callback = ShopListDiffCallback(shopItemList, value)
//            val diff = DiffUtil.calculateDiff(callback)
//            diff.dispatchUpdatesTo(this)
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: OnShopItemLongClickListener? = null
    var onShopItemClickListener: OnShopItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )

//        val binding = ItemShopDisabledBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )

        //val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.onShopItemLongClick(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.onShopItemClick(shopItem)
        }
        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }

            is ItemShopEnabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class ShopItemViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

//    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val tvName = view.findViewById<TextView>(R.id.tv_name)
//        val tvCount = view.findViewById<TextView>(R.id.tv_count)
//    }

    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    interface OnShopItemClickListener {
        fun onShopItemClick(shopItem: ShopItem)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
    }
}