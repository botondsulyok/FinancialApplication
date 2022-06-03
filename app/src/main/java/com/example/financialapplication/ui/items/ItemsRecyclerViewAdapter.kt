package com.example.financialapplication.ui.items

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.financialapplication.R
import com.example.financialapplication.ui.extensions.inflate
import com.example.financialapplication.ui.items.models.UiItem
import kotlinx.android.synthetic.main.item_items.view.*

class ItemsRecyclerViewAdapter(
    private val context: Context,
    private val onItemClicked: ((UiItem) -> Unit)? = null
) : ListAdapter<UiItem, ItemsRecyclerViewAdapter.ItemViewHolder>(ItemsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_items, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

   inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       fun bind(item: UiItem) {
           itemView.summaryText.text = item.summary
           itemView.sumText.apply {
               text = item.formattedSum
               isVisible = item.formattedSum != null && item.sum != 0
           }
           itemView.dateText.text = item.formattedPaid

           Glide
               .with(context)
               .load(item.iconDrawable)
               .placeholder(R.drawable.ic_baseline_image_24)
               .into(itemView.itemImage)

           itemView.setOnClickListener {
               onItemClicked?.invoke(item)
           }
       }
   }

    object ItemsDiffCallback: DiffUtil.ItemCallback<UiItem>() {
        override fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
            return oldItem == newItem
        }
    }
}