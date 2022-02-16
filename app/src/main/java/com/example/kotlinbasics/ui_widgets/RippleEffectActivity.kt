package com.example.kotlinbasics.ui_widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityRippleEffectBinding

class RippleEffectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRippleEffectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRippleEffectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.ripple_title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}