package com.bt.mp3.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.bt.base.ui.BaseActivity
import com.bt.mp3.R
import com.bt.mp3.databinding.ActivityMainBinding
import com.bt.mp3.extension.setupWithNavControllerKeepState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val sharedViewModel: MainSharedViewModel by viewModels()

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNav()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNav()
    }

    private fun setupBottomNav() {

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val navGraphIds = listOf(
            R.navigation.nav_my_music,
            R.navigation.nav_discover,
            R.navigation.nav_chart,
            R.navigation.nav_radio,
            R.navigation.nav_feed
        )

        bottomNavigationView.setupWithNavControllerKeepState(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostFragment,
            intent = intent
        )
    }
}
