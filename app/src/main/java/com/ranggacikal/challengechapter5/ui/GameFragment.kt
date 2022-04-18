package com.ranggacikal.challengechapter5.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ranggacikal.challengechapter5.databinding.FragmentGameBinding
import com.ranggacikal.challengechapter5.ui.viewModel.GameViewModel

class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    private val args: GameFragmentArgs by navArgs()
    private lateinit var viewModel: GameViewModel

    var selectedPlayer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        val pilihanLawan = args.pilihanLawan
        binding.tvPlayerNameGame.text = args.playerNameGame
        when (pilihanLawan) {
            "player" -> {
                binding.tvPlayer2.text = "Player 2"
                runVersusPlayer()
            }
            else -> {
                binding.tvPlayer2.text = "COM"
                runVersusCom()
            }
        }
        binding.imgRefresh.setOnClickListener {
            refreshAll()
        }
        binding.imgClose.setOnClickListener {
            val action = GameFragmentDirections.actionBackToMenu(args.playerNameGame)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun refreshAll() {
        binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
        binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
        binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
        binding.imgBatuCom.setBackgroundColor(Color.WHITE)
        binding.imgKertasCom.setBackgroundColor(Color.WHITE)
        binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
    }

    private fun runVersusPlayer() {
        validasiPlayer()
    }

    private fun validasiPlayer() {
        binding.imgBatuPlayer.setOnClickListener {
            binding.imgBatuPlayer.setBackgroundColor(Color.GRAY)
            binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
            binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
            selectedPlayer = 1
        }

        binding.imgGuntingPlayer.setOnClickListener {
            binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
            binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
            binding.imgGuntingPlayer.setBackgroundColor(Color.GRAY)
            selectedPlayer = 3
        }

        binding.imgKertasPlayer.setOnClickListener {
            binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
            binding.imgKertasPlayer.setBackgroundColor(Color.GRAY)
            binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
            selectedPlayer = 2
        }

        binding.imgBatuCom.setOnClickListener {
            binding.imgBatuCom.setBackgroundColor(Color.GRAY)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
            binding.tvPilihanCom.text = "Pemain 2 memilih batu"
            viewModel.validasiHasilPlayer(selectedPlayer, 1)
            when (viewModel.hasilValidasi()) {
                "win" -> {
                    viewModel.showDialogWin(
                        args.playerNameGame, "MENANG",
                        requireContext(), binding.root
                    )
                    if (viewModel.dismissViewModel()){
                        resetDismissBatu()
                    }
                }

                "lose" -> {
                    viewModel.showDialogLose(
                        args.pilihanLawan, "MENANG",
                        requireContext(), binding.root, args.playerNameGame
                    )
                    if (viewModel.dismissViewModel()){
                        resetDismissBatu()
                    }
                }
                else -> {
                    viewModel.showDialogDraw(
                        args.playerNameGame, "SERI",
                        requireContext(), binding.root
                    )
                    if (viewModel.dismissViewModel()){
                        resetDismissBatu()
                    }
                }
            }
        }

        binding.imgKertasCom.setOnClickListener {
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.GRAY)
            binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
            binding.tvPilihanCom.text = "Pemain 2 memilih Kertas"
            viewModel.validasiHasilPlayer(selectedPlayer, 2)
            when (viewModel.hasilValidasi()) {
                "win" ->
                    viewModel.showDialogWin(
                        args.playerNameGame, "MENANG",
                        requireContext(), binding.root
                    )

                "lose" ->
                    viewModel.showDialogLose(
                        args.pilihanLawan, "MENANG",
                        requireContext(), binding.root, args.playerNameGame
                    )
                else -> viewModel.showDialogDraw(
                    args.playerNameGame, "SERI",
                    requireContext(), binding.root
                )
            }
        }

        binding.imgGuntingCom.setOnClickListener {
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
            binding.tvPilihanCom.text = "Pemain 2 memilih Gunting"
            viewModel.validasiHasilPlayer(selectedPlayer, 3)
            when (viewModel.hasilValidasi()) {
                "win" ->
                    viewModel.showDialogWin(
                        args.playerNameGame, "MENANG",
                        requireContext(), binding.root
                    )

                "lose" ->
                    viewModel.showDialogLose(
                        args.pilihanLawan, "MENANG",
                        requireContext(), binding.root, args.playerNameGame
                    )
                else -> viewModel.showDialogDraw(
                    args.playerNameGame, "SERI",
                    requireContext(), binding.root
                )
            }
        }
    }

    private fun resetDismissBatu() {
        binding.imgBatuCom.setBackgroundColor(Color.WHITE)
        binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
        binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
        binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
    }

    private fun runVersusCom() {
        selectedPlayerCom()
    }

    private fun selectedPlayerCom() {
        binding.imgBatuPlayer.setOnClickListener {
            binding.imgBatuPlayer.setBackgroundColor(Color.GRAY)
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
            binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
            viewModel.validasiHasilCom(1)
            when (viewModel.setCom()) {
                1 -> {
                    binding.imgBatuCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih batu"
                }
                2 ->{
                    binding.imgKertasCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih kertas"
                }
                else ->{
                    binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih Gunting"
                }
            }
            when (viewModel.hasilValidasi()) {
                "win" ->
                    viewModel.showDialogWin(
                        args.playerNameGame, "MENANG",
                        requireContext(), binding.root
                    )

                "lose" ->
                    viewModel.showDialogLose(
                        args.pilihanLawan, "MENANG",
                        requireContext(), binding.root, args.playerNameGame
                    )
                else -> viewModel.showDialogDraw(
                    args.playerNameGame, "SERI",
                    requireContext(), binding.root
                )
            }
        }

        binding.imgKertasPlayer.setOnClickListener {
            binding.imgKertasPlayer.setBackgroundColor(Color.GRAY)
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
            binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
            binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
            viewModel.validasiHasilCom(2)
            when (viewModel.setCom()) {
                1 -> binding.imgBatuCom.setBackgroundColor(Color.GRAY)
                2 -> binding.imgKertasCom.setBackgroundColor(Color.GRAY)
                else -> binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
            }
            when (viewModel.hasilValidasi()) {
                "win" ->
                    viewModel.showDialogWin(
                        args.playerNameGame, "MENANG",
                        requireContext(), binding.root
                    )

                "lose" ->
                    viewModel.showDialogLose(
                        args.pilihanLawan, "MENANG",
                        requireContext(), binding.root, args.playerNameGame
                    )
                else -> viewModel.showDialogDraw(
                    args.playerNameGame, "SERI",
                    requireContext(), binding.root
                )
            }
        }

        binding.imgGuntingPlayer.setOnClickListener {
            binding.imgGuntingPlayer.setBackgroundColor(Color.GRAY)
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
            binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
            viewModel.validasiHasilCom(1)
            when (viewModel.setCom()) {
                1 -> binding.imgBatuCom.setBackgroundColor(Color.GRAY)
                2 -> binding.imgKertasCom.setBackgroundColor(Color.GRAY)
                else -> binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
            }
            when (viewModel.hasilValidasi()) {
                "win" ->
                    viewModel.showDialogWin(
                        args.playerNameGame, "MENANG",
                        requireContext(), binding.root
                    )

                "lose" ->
                    viewModel.showDialogLose(
                        args.pilihanLawan, "MENANG",
                        requireContext(), binding.root, args.playerNameGame
                    )
                else -> viewModel.showDialogDraw(
                    args.playerNameGame, "SERI",
                    requireContext(), binding.root
                )
            }
        }
    }


}