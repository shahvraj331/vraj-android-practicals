package com.example.kotlinbasics.ui_widgets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinbasics.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val goToRegistrationForm = Intent(this@HomeScreenActivity, RegistrationFormActivity::class.java)
            startActivity(goToRegistrationForm)
        }

        binding.ivCompanyLogo.setOnLongClickListener {
            val goToRippleEffectsActivity = Intent(this@HomeScreenActivity, RippleEffectActivity::class.java)
            startActivity(goToRippleEffectsActivity)
            return@setOnLongClickListener true
        }
    }
}