package com.mosjak.itemist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showSplash()
        setContent {
            Column {
                Text(text = "ABC")
                Text(text = "DEF")
            }
        }
    }

    private fun showSplash() {
        val splashScreen = installSplashScreen()
        val start = System.currentTimeMillis()
        splashScreen.setKeepOnScreenCondition {
            System.currentTimeMillis() - start < 500
        }
    }
}
