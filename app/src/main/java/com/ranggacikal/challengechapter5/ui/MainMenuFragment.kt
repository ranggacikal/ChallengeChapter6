package com.ranggacikal.challengechapter5.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.databinding.FragmentMainMenuBinding
import com.ranggacikal.challengechapter5.databinding.FragmentThirdLandingBinding

class MainMenuFragment : Fragment() {

    lateinit var binding: FragmentMainMenuBinding
    private val args: MainMenuFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerName = args.playerName

        binding.tvPlayerNameMainMenu.text = playerName
        binding.tvPlayerNameVsComMainMenu.text = playerName

        val snackBar = Snackbar.make(binding.root, "Selamat Datang $playerName", Snackbar.LENGTH_LONG)
        snackBar.setAction("Tutup", View.OnClickListener {
            snackBar.dismiss()
        })
        snackBar.show()

        binding.imgVsPlayerMainMenu.setOnClickListener {
            val action = MainMenuFragmentDirections.actionToGame(playerName, "player")
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.imgVsComMainMenu.setOnClickListener {
            val action = MainMenuFragmentDirections.actionToGame(playerName, "com")
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.imgLeaderBoard.setOnClickListener {
            val actionHistory = MainMenuFragmentDirections.actionToHistory()
            Navigation.findNavController(binding.root).navigate(actionHistory)
        }
    }
}