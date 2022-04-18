package com.ranggacikal.challengechapter5.ui.landingScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.databinding.FragmentLandingBinding
import com.ranggacikal.challengechapter5.databinding.FragmentSecondLandingBinding

class SecondLandingFragment : Fragment() {

    lateinit var binding: FragmentSecondLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}