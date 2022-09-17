package com.a706012110039.peternakan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.a706012110039.peternakan.databinding.ActivitySplashBinding

class splashActivity : AppCompatActivity() {
    private lateinit var viewbind: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewbind = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewbind.root)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}