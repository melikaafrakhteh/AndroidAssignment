package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ir.miare.androidcodechallenge.domain.model.ItemModel
import ir.miare.androidcodechallenge.domain.model.LeagueModel
import ir.miare.androidcodechallenge.domain.model.PlayerModel

class RankingAdapter(
    private val onPlayerClicked: (PlayerModel) -> Unit,
) : ListAdapter<ItemModel, RankingViewHolder>(DiffUtilCallback()) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is LeagueModel) {
            ViewHolderFactory.LEAGUE_VIEW_HOLDER
        } else {
            ViewHolderFactory.PLAYER_VIEW_HOLDER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        return ViewHolderFactory(parent, viewType).create()
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(
            data = getItem(position),
            onClicked = {
                if (getItem(position) is PlayerModel)
                    onPlayerClicked.invoke(getItem(position) as PlayerModel)
            }
        )
    }

}

