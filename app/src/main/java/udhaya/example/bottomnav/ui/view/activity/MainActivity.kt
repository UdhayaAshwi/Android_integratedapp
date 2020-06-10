package udhaya.example.bottomnav.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import udhaya.example.bottomnav.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_toolbar)

        val bottomnavigationview: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        var navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {

                R.id.home2_fragment -> bottomnavigationview.visibility = View.GONE
                R.id.settings -> bottomnavigationview.visibility = View.GONE
                else -> bottomnavigationview.visibility = View.VISIBLE
            }
        }

        NavigationUI.setupWithNavController(bottomnavigationview, navController)
    }

}


