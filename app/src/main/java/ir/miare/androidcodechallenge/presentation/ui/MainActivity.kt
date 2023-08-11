package ir.miare.androidcodechallenge.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.ActivityMainBinding
import ir.miare.androidcodechallenge.domain.model.Sorting
import ir.miare.androidcodechallenge.presentation.ranking.RankingFragment
import ir.miare.androidcodechallenge.presentation.ranking.RankingViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    private val viewModel: RankingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, RankingFragment())
            .commit()

        initialiseView()
    }

    private fun initialiseView() {
        val sortingMap = mapOf(
            R.id.rb_team_ranking to Sorting.TeamAndLeagueRanking,
            R.id.rb_most_goal_player to Sorting.PlayerMostGoal,
            R.id.rb_average_goal_league to Sorting.AverageGoal,
            R.id.rb_none to Sorting.None
        )

        binding?.rgSort?.setOnCheckedChangeListener { group, checkedId ->
            val sortingType = sortingMap[checkedId] ?: Sorting.None
            viewModel.changeSortingByType(sortingType)
        }
    }

}
