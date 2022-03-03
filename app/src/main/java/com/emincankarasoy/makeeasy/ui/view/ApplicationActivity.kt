package com.emincankarasoy.makeeasy.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.databinding.ActivityApplicationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ApplicationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_application)

        val navHost = supportFragmentManager.findFragmentById(R.id.applicationNavHostFragment) as NavHostFragment
        val navController = navHost.navController

        setupToolbar(navController)
        setupBottomNavigationView(navController)
        setupDestinationChangedListener(navController)
    }

    private fun setupBottomNavigationView(navController:NavController){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.applicationBottomNavigationView)
        bottomNavigationView?.setupWithNavController(navController)
    }

    private fun setupToolbar(navController: NavController){
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,
                R.id.walletFragment,
                R.id.taskFragment,
                R.id.profileFragment))
        binding.applicationToolbar.setupWithNavController(navController,appBarConfiguration)
    }

    private fun setupDestinationChangedListener(navController: NavController){
        navController.addOnDestinationChangedListener{ _ , destination ,_ ->
            when(destination.id){
                R.id.homeFragment -> Log.d("Destination","Home")
                R.id.walletFragment ->  Log.d("Destination", "Wallet")
                R.id.taskFragment -> Log.d("Destination","Task")
                R.id.profileFragment -> Log.d("Destination","Profile")
            }
        }
    }
}