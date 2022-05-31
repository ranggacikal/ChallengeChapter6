package com.ranggacikal.challengechapter5.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.ranggacikal.challengechapter5.databinding.FragmentGameBinding
import com.ranggacikal.challengechapter5.db.History
import com.ranggacikal.challengechapter5.db.HistoryDatabase
import com.ranggacikal.challengechapter5.ui.model.HasilValidasiPlayerData
import com.ranggacikal.challengechapter5.ui.presenter.GamePresenter
import com.ranggacikal.challengechapter5.ui.presenter.GameView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class GameFragment : Fragment(), GameView {

    lateinit var binding: FragmentGameBinding
    private val args: GameFragmentArgs by navArgs()
    lateinit var presenter: GamePresenter
    private var db: HistoryDatabase? = null

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
        presenter = GamePresenter(this)
        val pilihanLawan = args.pilihanLawan
        binding.tvPlayerNameGame.text = args.playerNameGame
        db = HistoryDatabase.getInstance(requireContext())

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
            presenter.validasiHasilPlayer(selectedPlayer, 1)
        }

        binding.imgKertasCom.setOnClickListener {
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.GRAY)
            binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
            binding.tvPilihanCom.text = "Pemain 2 memilih Kertas"
            presenter.validasiHasilPlayer(selectedPlayer, 2)
        }

        binding.imgGuntingCom.setOnClickListener {
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
            binding.tvPilihanCom.text = "Pemain 2 memilih Gunting"
            presenter.validasiHasilPlayer(selectedPlayer, 3)
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
            presenter.validasiHasilCom(1)
            when (presenter.setCom()) {
                1 -> {
                    binding.imgBatuCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih batu"
                }
                2 -> {
                    binding.imgKertasCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih kertas"
                }
                else -> {
                    binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih Gunting"
                }
            }
        }

        binding.imgKertasPlayer.setOnClickListener {
            binding.imgKertasPlayer.setBackgroundColor(Color.GRAY)
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgGuntingCom.setBackgroundColor(Color.WHITE)
            binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
            binding.imgGuntingPlayer.setBackgroundColor(Color.WHITE)
            presenter.validasiHasilCom(2)
            when (presenter.setCom()) {
                1 -> {
                    binding.imgBatuCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih batu"
                }
                2 -> {
                    binding.imgKertasCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih kertas"
                }
                else -> {
                    binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih Gunting"
                }
            }
        }

        binding.imgGuntingPlayer.setOnClickListener {
            binding.imgGuntingPlayer.setBackgroundColor(Color.GRAY)
            binding.imgBatuCom.setBackgroundColor(Color.WHITE)
            binding.imgKertasCom.setBackgroundColor(Color.WHITE)
            binding.imgBatuPlayer.setBackgroundColor(Color.WHITE)
            binding.imgKertasPlayer.setBackgroundColor(Color.WHITE)
            presenter.validasiHasilCom(1)
            when (presenter.setCom()) {
                1 -> {
                    binding.imgBatuCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih batu"
                }
                2 -> {
                    binding.imgKertasCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih kertas"
                }
                else -> {
                    binding.imgGuntingCom.setBackgroundColor(Color.GRAY)
                    binding.tvPilihanCom.text = "CPU 2 memilih Gunting"
                }
            }
        }
    }

    override fun hasilValidasiPlayer(hasilValidasiPlayerData: HasilValidasiPlayerData) {
        when (hasilValidasiPlayerData.hasilValidasiPlayer) {
            "win" -> {
                val currentDate = LocalDateTime.now()
                presenter.showDialogWin(
                    args.playerNameGame, "MENANG",
                    requireContext(), binding.root
                )
                GlobalScope.launch {
                    val historyPlayerWin =
                        History(null, args.playerNameGame, "MENANG", currentDate.toString())
                    db?.historyDao()?.insert(historyPlayerWin)
                }
            }

            "lose" -> {
                presenter.showDialogLose(
                    args.pilihanLawan, "MENANG",
                    requireContext(), binding.root, args.playerNameGame
                )
                val currentDate = LocalDateTime.now()
                GlobalScope.launch {
                    val historyPlayerLose =
                        History(null, args.pilihanLawan, "MENANG", currentDate.toString())
                    db?.historyDao()?.insert(historyPlayerLose)
                }
            }
            else -> {
                presenter.showDialogDraw(
                    args.playerNameGame, "SERI",
                    requireContext(), binding.root
                )
                val currentDate = LocalDateTime.now()
                GlobalScope.launch {
                    val historyPlayerDraw =
                        History(null, args.playerNameGame, "SERI", currentDate.toString())
                    db?.historyDao()?.insert(historyPlayerDraw)
                }
            }
        }
    }


}