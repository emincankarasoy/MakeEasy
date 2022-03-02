package com.emincankarasoy.makeeasy.ui

import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.ui.view.ApplicationActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        goToNextScreen()
        finish()

    }

    private fun goToNextScreen(){
        startActivity(Intent(this, ApplicationActivity::class.java))
        finish()
    }
}