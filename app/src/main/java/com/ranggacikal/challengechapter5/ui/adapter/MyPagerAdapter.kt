package com.ranggacikal.challengechapter5.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ranggacikal.challengechapter5.ui.landingScreen.FirstLandingFragment
import com.ranggacikal.challengechapter5.ui.landingScreen.SecondLandingFragment
import com.ranggacikal.challengechapter5.ui.landingScreen.ThirdLandingFragment

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val pages = listOf(
        FirstLandingFragment(),
        SecondLandingFragment(),
        ThirdLandingFragment()
    )

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }
}