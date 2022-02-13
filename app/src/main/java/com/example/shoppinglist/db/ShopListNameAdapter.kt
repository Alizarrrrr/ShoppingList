package com.example.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ListNameItemBinding
import com.example.shoppinglist.entities.ShoppingListNameItem


class ShopListNameAdapter(private val listener: Listener) : ListAdapter<ShoppingListNameItem, ShopListNameAdapter.ItemHolder> (ItemComporator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ListNameItemBinding.bind(view)
        fun setData(shopListNameItem: ShoppingListNameItem, listener: Listener) = with(binding){
            tvListName.text = shopListNameItem.name

            tvTime.text = shopListNameItem.time
            itemView.setOnClickListener{
                listener.onClickItem(shopListNameItem)
            }
            imDelete.setOnClickListener{
                listener.deleteItem(shopListNameItem.id!!)
            }
            imEdit.setOnClickListener{
                listener.editListName(shopListNameItem)
            }

        }
        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(
                    LayoutInflater.from(parent.context).
                    inflate(R.layout.list_name_item, parent, false))
            }
        }
    }

    class ItemComporator : DiffUtil.ItemCallback<ShoppingListNameItem>(){
        override fun areItemsTheSame(oldItem: ShoppingListNameItem, newItem: ShoppingListNameItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingListNameItem, newItem: ShoppingListNameItem): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener{
        fun deleteItem(id: Int)
        fun editListName(shopListNameItem: ShoppingListNameItem)
        fun onClickItem(shopListNameItem: ShoppingListNameItem)
    }


}