package com.udhaya.integratedapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/* Adapter to render the tabs required for the
   Viewpager
*/

class WeatherandSaveDataAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val titleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        //Function to get the fragment to be displayed in the viewpager
        return fragmentList[position]
    }

    override fun getCount(): Int {
        //Function to get the number of fragments to be displayed in the viewpager
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        //Function to add the fragment to the viewpager
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //Function to get the title of each tab in the viewpager
        return titleList[position]
    }
}