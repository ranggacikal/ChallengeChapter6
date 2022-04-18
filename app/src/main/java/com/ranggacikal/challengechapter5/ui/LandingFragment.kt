package com.ranggacikal.challengechapter5.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.databinding.FragmentLandingBinding
import com.ranggacikal.challengechapter5.ui.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_landing.*
import me.relex.circleindicator.CircleIndicator

class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerLanding.adapter = MyPagerAdapter(childFragmentManager)
        val current = binding.viewPagerLanding.currentItem
        Log.d("currentCek", "onViewCreated: $current")
        binding.circleIndicator.setViewPager(viewPagerLanding)

    }
}