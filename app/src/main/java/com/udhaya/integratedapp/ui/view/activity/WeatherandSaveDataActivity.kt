package com.udhaya.integratedapp.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udhaya.integratedapp.R
import com.udhaya.integratedapp.ui.adapter.WeatherandSaveDataAdapter
import com.udhaya.integratedapp.ui.view.fragment.SaveUserDataFragment
import com.udhaya.integratedapp.ui.view.fragment.WeatherReportFragment
import kotlinx.android.synthetic.main.activity_viewpager.*

/* Activity to host the viewpager fragments
*/

class WeatherandSaveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        val adapter = WeatherandSaveDataAdapter(supportFragmentManager)
        adapter.addFragment(WeatherReportFragment(), "Weather")
        adapter.addFragment(SaveUserDataFragment(), "Add Famous Places")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}