package ir.miare.androidcodechallenge.presentation.ranking.adapter

import android.view.ViewGroup

class ViewHolderFactory(
    private val parent: ViewGroup,
    private val viewType: Int,
) {
    companion object {
        const val LEAGUE_VIEW_HOLDER = 1
        const val PLAYER_VIEW_HOLDER = 2
    }

    fun create(): RankingViewHolder {
        return when (viewType) {
            LEAGUE_VIEW_HOLDER -> {
                RankingLeagueViewHolder.create(parent)
            }
            else -> {
                RankingPlayerViewHolder.create(parent)
            }
        }
    }
}