package ir.miare.androidcodechallenge.presentation.ranking

import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.util.ViewResource

data class RankingState(
    val data: ViewResource<List<RankingModel>> = ViewResource.NotAvailable,
)