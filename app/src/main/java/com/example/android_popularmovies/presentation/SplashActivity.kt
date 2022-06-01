package com.example.android_popularmovies.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.android_popularmovies.R
import com.example.android_popularmovies.presentation.movie.view.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.myLooper()!!).postDelayed({


            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, DELAY_IN_IN_TRANSITION)
    }

    companion object {
        private const val DELAY_IN_IN_TRANSITION = 2000L
    }
}
