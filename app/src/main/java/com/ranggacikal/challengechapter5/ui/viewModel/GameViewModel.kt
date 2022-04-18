package com.ranggacikal.challengechapter5.ui.viewModel

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.ui.GameFragment
import com.ranggacikal.challengechapter5.ui.GameFragmentDirections

class GameViewModel : ViewModel() {

    var pilihanPlayer: String = ""
    var pilihanCom: String = ""
    var pilihanPlayer2: String = ""
    var hasilValidasiPlayer: String = ""
    var isDismiss: Boolean = false
    var numberCom: Int = 0

    fun validasiHasilCom(player: Int) {
        when (player) {
            1 -> pilihanPlayer = "batu"
            2 -> pilihanPlayer = "kertas"
            3 -> pilihanPlayer = "gunting"
        }

        Log.d("pilihanPlayer", "pilihanUser: $pilihanPlayer")

        val randomNumber = (1..3).random()

        numberCom = randomNumber

        when (randomNumber) {
            1 -> pilihanCom = "batu"
            2 -> pilihanCom = "kertas"
            3 -> pilihanCom = "gunting"
        }

        Log.d("pilihanComputer", "pilihanCom: $pilihanCom")

        when {
            pilihanPlayer == "batu" && pilihanCom == "gunting" -> hasilValidasiPlayer = "win"
            pilihanPlayer == "kertas" && pilihanCom == "batu" -> hasilValidasiPlayer = "win"
            pilihanPlayer == "gunting" && pilihanCom == "kertas" -> hasilValidasiPlayer = "win"
            pilihanPlayer == "gunting" && pilihanCom == "batu" -> hasilValidasiPlayer = "lose"
            pilihanPlayer == "batu" && pilihanCom == "kertas" -> hasilValidasiPlayer = "lose"
            pilihanPlayer == "kertas" && pilihanCom == "gunting" -> hasilValidasiPlayer = "lose"
            else -> hasilValidasiPlayer = "draw"
        }
    }

    fun validasiHasilPlayer(player: Int, player2: Int) {
        when (player) {
            1 -> pilihanPlayer = "batu"
            2 -> pilihanPlayer = "kertas"
            3 -> pilihanPlayer = "gunting"
        }

        Log.d("pilihanPlayer", "pilihanUser: $pilihanPlayer")

        when (player2) {
            1 -> pilihanPlayer2 = "batu"
            2 -> pilihanPlayer2 = "kertas"
            3 -> pilihanPlayer2 = "gunting"
        }

        hasilValidasiPlayer = when {
            pilihanPlayer == "batu" && pilihanPlayer2 == "gunting" -> "win"
            pilihanPlayer == "kertas" && pilihanPlayer2 == "batu" -> "win"
            pilihanPlayer == "gunting" && pilihanPlayer2 == "kertas" -> "win"
            pilihanPlayer == "gunting" && pilihanPlayer2 == "batu" -> "lose"
            pilihanPlayer == "batu" && pilihanPlayer2 == "kertas" -> "lose"
            pilihanPlayer == "kertas" && pilihanPlayer2 == "gunting" -> "lose"
            else -> "draw"
        }
    }

    fun setCom(): Int {
        return numberCom

    }

    fun hasilValidasi(): String {
        return hasilValidasiPlayer
    }

    fun showDialogWin(name: String, result: String, context: Context, root: ConstraintLayout) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_hasil)
        val tvPlayerName = dialog.findViewById<TextView>(R.id.tv_player_name_dialog)
        val tvResult = dialog.findViewById<TextView>(R.id.tv_result_dialog)
        val btnRematch = dialog.findViewById<Button>(R.id.btn_rematch_dialog)
        val btnBackMenu = dialog.findViewById<Button>(R.id.btn_to_menu_dialog)
        tvPlayerName.text = name
        tvResult.text = result
        btnRematch.setOnClickListener {
            dialog.dismiss()
            isDismiss = true
        }
        btnBackMenu.setOnClickListener {
            dialog.dismiss()
            val action = GameFragmentDirections.actionBackToMenu(name)
            Navigation.findNavController(root).navigate(action)
        }
        dialog.show()
    }

    fun showDialogLose(
        name: String,
        result: String,
        context: Context,
        root: ConstraintLayout,
        playerName: String
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_hasil)
        val tvPlayerName = dialog.findViewById<TextView>(R.id.tv_player_name_dialog)
        val tvResult = dialog.findViewById<TextView>(R.id.tv_result_dialog)
        val btnRematch = dialog.findViewById<Button>(R.id.btn_rematch_dialog)
        val btnBackMenu = dialog.findViewById<Button>(R.id.btn_to_menu_dialog)
        tvPlayerName.text = name
        tvResult.text = result
        btnRematch.setOnClickListener {
            dialog.dismiss()
            isDismiss = true
        }
        btnBackMenu.setOnClickListener {
            dialog.dismiss()
            val action = GameFragmentDirections.actionBackToMenu(playerName)
            Navigation.findNavController(root).navigate(action)
        }
        dialog.show()
    }

    fun showDialogDraw(name: String, result: String, context: Context, root: ConstraintLayout) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_hasil)
        val tvPlayerName = dialog.findViewById<TextView>(R.id.tv_player_name_dialog)
        val tvResult = dialog.findViewById<TextView>(R.id.tv_result_dialog)
        val btnRematch = dialog.findViewById<Button>(R.id.btn_rematch_dialog)
        val btnBackMenu = dialog.findViewById<Button>(R.id.btn_to_menu_dialog)
        tvPlayerName.visibility = View.GONE
        tvResult.text = result
        btnRematch.setOnClickListener {
            dialog.dismiss()
            isDismiss = true
            dismissViewModel()
        }
        btnBackMenu.setOnClickListener {
            dialog.dismiss()
            val action = GameFragmentDirections.actionBackToMenu(name)
            Navigation.findNavController(root).navigate(action)
        }
        dialog.show()
    }

    fun dismissViewModel(): Boolean {
        return isDismiss
    }

}