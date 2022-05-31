package com.ranggacikal.challengechapter5.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.userName

class SplashFragment : Fragment() {

    lateinit var sharedPreference: PreferenceHelper
    val CUSTOM_PREF_NAME = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = PreferenceHelper
        val prefs = sharedPreference.customPreference(requireContext(), CUSTOM_PREF_NAME)
        if (prefs.userName.toString() == "") {
            Handler().postDelayed({
                findNavController()
                    .navigate(
                        R.id.action_to_landing,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(
                                R.id.splashFragment,
                                true
                            ).build()
                    )
            }, 3000)
        }else{
            val bundle = bundleOf("playerName" to prefs.userName.toString())
            Handler().postDelayed({
                findNavController()
                    .navigate(
                        R.id.action_splash_to_main,
                        bundle,
                        NavOptions.Builder()
                            .setPopUpTo(
                                R.id.splashFragment,
                                true
                            ).build()
                    )
            }, 3000)
        }

    }
}
