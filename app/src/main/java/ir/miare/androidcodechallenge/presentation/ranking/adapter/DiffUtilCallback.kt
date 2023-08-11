package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ir.miare.androidcodechallenge.domain.model.ItemModel

class DiffUtilCallback : DiffUtil.ItemCallback<ItemModel>() {

    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem == newItem
    }
}