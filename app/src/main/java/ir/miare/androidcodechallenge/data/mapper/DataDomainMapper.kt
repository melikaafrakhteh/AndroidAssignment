package ir.miare.androidcodechallenge.data.mapper

import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.data.model.Team
import ir.miare.androidcodechallenge.domain.model.LeagueModel
import ir.miare.androidcodechallenge.domain.model.PlayerModel
import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.domain.model.TeamModel
import ir.miare.androidcodechallenge.util.Mapper
import javax.inject.Inject

class DataDomainMapper @Inject constructor() : Mapper<FakeData, RankingModel> {
    override fun convertFirstObjectToSecond(first: FakeData): RankingModel {
        return RankingModel(
            league = mapFromLeague(first.league),
            players = first.players.map { player -> mapFromPlayer(player) },
        )
    }

    override fun convertSecondObjectToFirst(second: RankingModel): FakeData {
        return FakeData(
            league = mapToLeague(second.league),
            players = second.players.map { playerModel -> mapToPlayer(playerModel) },
        )
    }

    private fun mapToLeague(leagueModel: LeagueModel): League {
        return League(
            name = leagueModel.name,
            country = leagueModel.country,
            rank = leagueModel.rank,
            totalMatches = leagueModel.totalMatches,
        )
    }

    private fun mapFromLeague(league: League): LeagueModel {
        return LeagueModel(
            name = league.name,
            country = league.country,
            rank = league.rank,
            totalMatches = league.totalMatches,
        )
    }

    private fun mapToPlayer(playerModel: PlayerModel): Player {
        return Player(
            name = playerModel.name,
            team = mapToTeam(playerModel.team),
            totalGoal = playerModel.totalGoal,
        )
    }

    private fun mapFromPlayer(player: Player): PlayerModel {
        return PlayerModel(
            name = player.name,
            team = mapFromTeam(player.team),
            totalGoal = player.totalGoal,
        )
    }

    private fun mapToTeam(teamModel: TeamModel): Team {
        return Team(
            name = teamModel.name,
            rank = teamModel.rank
        )
    }

    private fun mapFromTeam(team: Team): TeamModel {
        return TeamModel(
            name = team.name,
            rank = team.rank
        )
    }
}