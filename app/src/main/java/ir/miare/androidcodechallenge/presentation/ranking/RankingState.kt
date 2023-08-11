package ir.miare.androidcodechallenge.presentation.ranking

import ir.miare.androidcodechallenge.domain.model.ItemModel
import ir.miare.androidcodechallenge.util.ViewResource

data class RankingState(
    val data: ViewResource<List<ItemModel>> = ViewResource.NotAvailable,
)