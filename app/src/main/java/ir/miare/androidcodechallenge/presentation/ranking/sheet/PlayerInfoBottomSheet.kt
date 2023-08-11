package ir.miare.androidcodechallenge.presentation.ranking.sheet

import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.BottomSheetPlayerInfoBinding
import ir.miare.androidcodechallenge.domain.model.PlayerModel
import ir.miare.androidcodechallenge.domain.model.TeamModel

class PlayerInfoBottomSheet() : BottomSheetDialogFragment() {

    private var _binding: BottomSheetPlayerInfoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.bottom_sheet_player_info, container, false)
        _binding = BottomSheetPlayerInfoBinding.bind(view)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val playerArg = args?.getParcelable<PlayerArg>(ARG) as PlayerArg

        with(binding) {
            tvPlayerName.text = playerArg.player.name
            tvTeamName.text = playerArg.player.team.name
            tvTotalGoals.text = playerArg.player.totalGoal.toString()
            btnBack.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val ARG = "ARG"
        fun newInstance(
            player: PlayerModel,
        ): PlayerInfoBottomSheet = PlayerInfoBottomSheet().apply {
            arguments = bundleOf(ARG to PlayerArg(player))
        }
    }

    @JvmInline
    private value class PlayerArg(
        val player: PlayerModel,
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            PlayerModel(
                parcel.readString()!!,
                TeamModel(
                    parcel.readString()!!,
                    parcel.readInt(),
                ),
                parcel.readInt(),
            )
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(player.name)
            parcel.writeString(player.team.name)
            parcel.writeInt(player.team.rank)
            parcel.writeInt(player.totalGoal)
        }

        override fun describeContents() = 0

        companion object CREATOR : Parcelable.Creator<PlayerArg> {
            override fun createFromParcel(parcel: Parcel): PlayerArg = PlayerArg(parcel)
            override fun newArray(size: Int): Array<PlayerArg?> = arrayOfNulls(size)
        }

    }

}
