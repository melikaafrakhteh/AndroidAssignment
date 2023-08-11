package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.domain.model.ItemModel


abstract class RankingViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(data: ItemModel, onClicked: (ItemModel) -> Unit)
}