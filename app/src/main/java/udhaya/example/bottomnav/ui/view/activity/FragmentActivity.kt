package udhaya.example.bottomnav.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_viewpager.*
import udhaya.example.bottomnav.R
import udhaya.example.bottomnav.ui.view.fragment.Retrofit_fragment
import udhaya.example.bottomnav.ui.view.fragment.Dummy_fragment
import udhaya.example.bottomnav.ui.view.fragment.Realm_fragment

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Retrofit_fragment(), "Weather")
        adapter.addFragment(Realm_fragment(), "Realm")
        adapter.addFragment(Dummy_fragment(), "Dummy")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}