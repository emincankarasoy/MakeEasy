package com.emincankarasoy.makeeasy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.source.CloudRepository
import com.emincankarasoy.makeeasy.databinding.ActivityApplicationBinding
import com.emincankarasoy.makeeasy.ui.view.task.TaskFragment
import com.emincankarasoy.makeeasy.ui.view.wallet.TransactionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*

class ApplicationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityApplicationBinding
    private lateinit var navController : NavController

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        binding = DataBindingUtil.setContentView(this,R.layout.activity_application)

        val navHost = supportFragmentManager.findFragmentById(R.id.applicationNavHostFragment) as NavHostFragment
        navController = navHost.navController

        val sharedPreferences = getSharedPreferences("make_easy",MODE_PRIVATE)

        if (sharedPreferences.getString("uuid","0") == "0"){
            sharedPreferences.edit().putString("uuid",UUID.randomUUID().toString()).apply()
        }

        Log.i("UUID", sharedPreferences.getString("uuid","None").toString())

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

    private fun setupDestinationChangedListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                //Home Fragment Tool Bar Configuration
                R.id.homeFragment -> {
                    binding.applicationToolbar.menu.clear()
                }
                //Wallet Fragment Tool Bar Configuration
                R.id.walletFragment -> {
                    binding.applicationToolbar.menu.clear()
                    binding.applicationToolbar.inflateMenu(R.menu.wallet_toolbar_menu)
                    binding.applicationToolbar.setOnMenuItemClickListener { it -> setWalletOptionsMenuListener(it)}
                }
                //Task Fragment Tool Bar Configuration
                R.id.taskFragment -> {
                    binding.applicationToolbar.menu.clear()
                    binding.applicationToolbar.inflateMenu(R.menu.task_toolbar_menu)
                    binding.applicationToolbar.setOnMenuItemClickListener { it -> setTaskOptionsMenuListener(it)}
                }
                //Profile Fragment Tool Bar Configuration
                R.id.profileFragment -> {
                    binding.applicationToolbar.menu.clear()
                    binding.applicationToolbar.inflateMenu(R.menu.profile_toolbar_menu)
                    binding.applicationToolbar.setOnMenuItemClickListener{it -> setProfileOptionsMenuListener(it)}

                }
            }
        }
    }

    private fun setWalletOptionsMenuListener(it : MenuItem) : Boolean{
        if (it.itemId == R.id.walletToolbarMenuAdd){
            TransactionFragment().show(supportFragmentManager,"Transaction Dialog Fragment")
            return true
        }
        return false
    }

    private fun setTaskOptionsMenuListener(it : MenuItem) : Boolean{
        if(it.itemId == R.id.taskToolbarMenuAdd){
            TaskFragment().show(supportFragmentManager,"Task Dialog Fragment")
            return true
        }
        return false
    }

    private fun setProfileOptionsMenuListener(it : MenuItem) : Boolean{
        val cloudRepository = CloudRepository(this)
        if(it.itemId == R.id.profileToolbarMenuUpload){
            AlertDialog.Builder(this).setTitle("Upload Data").setMessage("Are you sure you want to upload your data to cloud?")
                .setPositiveButton("Yes") { _, _ ->
                    cloudRepository.syncDataOnCloud()
                }.setNegativeButton("No"){dialog,_ ->
                    dialog.cancel()
                }.show()
            return true
        }else if(it.itemId == R.id.profileToolbarMenuDownload){
            AlertDialog.Builder(this).setTitle("Download Data").setMessage("Are you sure you want to download your data from cloud? ")
                .setPositiveButton("Yes") { _, _ ->
                    cloudRepository.syncDataOnLocal()
                }.setNegativeButton("No"){dialog,_ ->
                    dialog.cancel()
                }.show()
            return true
        }
        return false
    }

}