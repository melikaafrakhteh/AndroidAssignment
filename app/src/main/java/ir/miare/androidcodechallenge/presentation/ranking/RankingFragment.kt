package ir.miare.androidcodechallenge.presentation.ranking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.databinding.FragmentRankingBinding
import ir.miare.androidcodechallenge.domain.model.ItemModel
import ir.miare.androidcodechallenge.domain.model.PlayerModel
import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.presentation.ranking.adapter.RankingAdapter
import ir.miare.androidcodechallenge.presentation.ranking.sheet.PlayerInfoBottomSheet
import ir.miare.androidcodechallenge.util.ViewResource
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RankingFragment : Fragment() {
    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RankingViewModel by activityViewModels()

    private lateinit var rankingAdapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { rankingState ->
                initialiseView(rankingState)
            }
        }

    }

    private fun initialiseView(rankingState: RankingState) {
        when (val result = rankingState.data) {
            is ViewResource.Loading -> {
                binding.pbLoading.show()
            }
            is ViewResource.Error -> {
                binding.pbLoading.hide()
                showToast(result.error)
            }
            is ViewResource.Success -> {
                binding.pbLoading.hide()
                setUpRecyclerView(result.data)
            }
            ViewResource.NotAvailable -> Unit //do nothing
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun setUpRecyclerView(result: List<RankingModel>) {
        val items = ArrayList<ItemModel>()
        result.forEach {
            items.addAll(arrayListOf(RankingModel(it.league, it.players)))
        }
        rankingAdapter = RankingAdapter(
            items,
            onPlayerClicked = ::navigateToPlayerInfoSheet
        )
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rankingAdapter
            hasFixedSize()
        }
    }

    private fun navigateToPlayerInfoSheet(player: PlayerModel) {
        PlayerInfoBottomSheet(player).show(
            requireActivity().supportFragmentManager, ""
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}