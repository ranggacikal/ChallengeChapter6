package com.ranggacikal.challengechapter5.ui.landingScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.databinding.FragmentLandingBinding
import com.ranggacikal.challengechapter5.databinding.FragmentThirdLandingBinding
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.userName
import com.ranggacikal.challengechapter5.ui.LandingFragmentDirections
import com.ranggacikal.challengechapter5.ui.MainMenuFragment

class ThirdLandingFragment : Fragment() {

    lateinit var binding: FragmentThirdLandingBinding
    private val bundle = Bundle()
    val CUSTOM_PREF_NAME = "user_data"
    lateinit var sharedPreference: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = PreferenceHelper
        binding.edtNamaPemain.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = binding.edtNamaPemain.text.toString()
                if (name == ""){
                    binding.imgNextThirdLanding.visibility = View.GONE
                }else{
                    binding.imgNextThirdLanding.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.imgNextThirdLanding.setOnClickListener {
            val playerName = binding.edtNamaPemain.text.toString()
            val action = LandingFragmentDirections.actionToMainMenu(playerName)
            Navigation.findNavController(binding.clThirdLanding).navigate(action)
            val prefs = sharedPreference.customPreference(requireContext(), CUSTOM_PREF_NAME)
            prefs.userName = playerName
        }
    }
}