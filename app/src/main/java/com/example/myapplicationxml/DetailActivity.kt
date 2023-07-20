package com.example.myapplicationxml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplicationxml.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screeSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        screeSplash.setKeepOnScreenCondition{true}
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        Thread.sleep(1000)
        finish()
    }
}