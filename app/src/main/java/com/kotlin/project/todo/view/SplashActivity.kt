package com.kotlin.project.todo.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.project.R

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(Intent(this@SplashActivity, TaskActivity::class.java))
                finish()
            }
        }, 3000);
    }
}
