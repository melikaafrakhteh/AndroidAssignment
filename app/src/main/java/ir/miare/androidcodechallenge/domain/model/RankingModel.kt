package ir.miare.androidcodechallenge.domain.model

data class RankingModel(
    val league: LeagueModel,
    val players: List<PlayerModel>
)

data class LeagueModel(
    val name: String,
    val country: String,
    val rank: Int,
    val totalMatches: Int,
)

data class PlayerModel(
    val name: String,
    val team: TeamModel,
    val totalGoal: Int,
)

data class TeamModel(
    val name: String,
    val rank: Int
)