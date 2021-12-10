package com.nttdata.inspirationjournal2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.view.auth.AuthActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val backgroundImg: ImageView = findViewById(R.id.logo_splash)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_splash)
        backgroundImg.startAnimation(sideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        },3000)
    }
}