package com.emincankarasoy.makeeasy.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.databinding.ActivityApplicationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ApplicationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityApplicationBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        binding = DataBindingUtil.setContentView(this,R.layout.activity_application)

        val navHost = supportFragmentManager.findFragmentById(R.id.applicationNavHostFragment) as NavHostFragment
        navController = navHost.navController

        setupToolbar()
        setupBottomNavigationView(navController)
        setupDestinationChangedListener(navController)

        splashScreen.setKeepOnScreenCondition { false }
    }

    private fun setupBottomNavigationView(navController:NavController){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.applicationBottomNavigationView)
        bottomNavigationView?.setupWithNavController(navController)
    }

    private fun setupToolbar(){
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,
                R.id.walletFragment,
                R.id.taskFragment,
                R.id.profileFragment))
        binding.applicationToolbar.setupWithNavController(navController,appBarConfiguration)
    }

    private fun setupDestinationChangedListener(navController: NavController){
        navController.addOnDestinationChangedListener{ _ , destination , _ ->
            when (destination.id) {
                //Home Fragment Tool Bar Configuration
                R.id.homeFragment -> {
                    binding.applicationToolbar.menu.clear()
                }
                //Wallet Fragment Tool Bar Configuration
                R.id.walletFragment -> {
                    binding.applicationToolbar.menu.clear()
                    binding.applicationToolbar.inflateMenu(R.menu.wallet_toolbar_menu)
                }
                //Task Fragment Tool Bar Configuration
                R.id.taskFragment -> {
                    binding.applicationToolbar.menu.clear()
                    binding.applicationToolbar.inflateMenu(R.menu.task_toolbar_menu)
                }
                //Profile Fragment Tool Bar Configuration
                R.id.profileFragment -> {
                    binding.applicationToolbar.menu.clear()

                }
            }
        }
    }
}