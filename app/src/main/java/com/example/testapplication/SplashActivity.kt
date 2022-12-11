package com.example.testapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


/**
 * Splash activity that is the first page of our application which appears only for 5 seconds and
 * move to the second screen automatically
 *
 * @author Nishikanta
 */
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    companion object{
        private val SPLASH_SCREEN_TIME_OUT = 5000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            val i = Intent(
                this@SplashActivity,
                ListActivity::class.java
            )
            //Intent is used to switch from one activity to another.
            startActivity(i)
            //invoke the SecondActivity.
            finish()
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT.toLong())
    }
}