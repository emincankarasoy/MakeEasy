package com.emincankarasoy.makeeasy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.ui.view.ApplicationActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Default) {
            delay(4000L)
            goToNextScreen()
        }
    }

    private fun goToNextScreen(){
        startActivity(Intent(this, ApplicationActivity::class.java))
        finish()
    }
}