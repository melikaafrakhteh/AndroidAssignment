package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.miare.androidcodechallenge.databinding.ItemPlayerBinding
import ir.miare.androidcodechallenge.domain.model.ItemModel
import ir.miare.androidcodechallenge.domain.model.PlayerModel

class RankingPlayerViewHolder(
    private val binding: ItemPlayerBinding,
) : RankingViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): RankingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPlayerBinding.inflate(inflater, parent, false)
            return RankingPlayerViewHolder(binding)
        }
    }

    override fun bind(
        data: ItemModel,
        onClicked: (ItemModel) -> Unit
    ) {
        if (data !is PlayerModel) return
        with(binding) {
            playerName.text = data.name
            teamName.text = data.team.name
            rank.text = data.team.rank.toString()
            root.setOnClickListener { onClicked.invoke(data) }
        }
    }

}