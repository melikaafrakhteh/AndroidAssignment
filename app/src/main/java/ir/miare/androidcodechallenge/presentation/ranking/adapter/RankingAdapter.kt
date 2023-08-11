package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.domain.model.ItemModel
import ir.miare.androidcodechallenge.domain.model.LeagueModel
import ir.miare.androidcodechallenge.domain.model.PlayerModel
import ir.miare.androidcodechallenge.domain.model.RankingModel

class RankingAdapter(
    private val leagues: MutableList<ItemModel>,
    private val onPlayerClicked: (PlayerModel) -> Unit,
) : RecyclerView.Adapter<RankingViewHolder>() {

    private val items: MutableList<ItemModel> = mutableListOf()

    init {
        leagues.forEach { itemModel ->
            if (itemModel !is RankingModel) return@forEach
            items.add(itemModel.league)
            itemModel.players.forEach { playerModel ->
                items.add(playerModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is LeagueModel) {
            ViewHolderFactory.LEAGUE_VIEW_HOLDER
        } else {
            ViewHolderFactory.PLAYER_VIEW_HOLDER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        return ViewHolderFactory(parent, viewType).create()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(
            data = items[position],
            onClicked = {
                if (items[position] is PlayerModel)
                    onPlayerClicked.invoke(items[position] as PlayerModel)
            }
        )
    }

}

