package com.ineedyourcode.splashscreenapi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var isSplashScreenKeeping = true
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { isSplashScreenKeeping }

            setOnExitAnimationListener { viewProvider ->
                viewProvider.iconView.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .duration = 1000

                viewProvider.view.animate()
                    .alpha(0f)
                    .rotation(180f)
                    .withEndAction { viewProvider.remove() }
                    .duration = 1000
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(3000)
            isSplashScreenKeeping = false
        }
    }
}