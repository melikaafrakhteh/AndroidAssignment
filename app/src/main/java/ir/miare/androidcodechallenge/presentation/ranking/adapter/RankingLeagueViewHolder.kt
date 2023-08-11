package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.miare.androidcodechallenge.databinding.ItemLeagueBinding
import ir.miare.androidcodechallenge.domain.model.ItemModel
import ir.miare.androidcodechallenge.domain.model.LeagueModel

class RankingLeagueViewHolder(
    private val binding: ItemLeagueBinding,
) : RankingViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): RankingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLeagueBinding.inflate(inflater, parent, false)
            return RankingLeagueViewHolder(binding)
        }
    }

    override fun bind(
        data: ItemModel,
        onClicked: (ItemModel) -> Unit
    ) {
        if (data !is LeagueModel) return
        with(binding) {
            leagueName.text = data.name
            leagueCountry.text = data.country
        }
    }

}