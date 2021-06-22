package com.movieapp.ui

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.movieapp.R
import com.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupContentWindow()

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentContainerMain) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.header.btnMenu.setOnClickListener { view -> onMenuClick() }

    }

    private fun onMenuClick() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun setupContentWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

}