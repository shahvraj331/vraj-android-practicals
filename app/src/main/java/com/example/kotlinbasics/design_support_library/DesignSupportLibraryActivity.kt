package com.example.kotlinbasics.design_support_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityDesignSupporLibraryBinding

class DesignSupportLibraryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDesignSupporLibraryBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesignSupporLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.design_support_library)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close)
        actionBarDrawerToggle.syncState()
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.textInputLayout -> {
                    setUpFragment(TextInputLayoutFragment(), getString(R.string.textInputLayout))
                    closeDrawerLayoutIfOpen()
                }
                R.id.coordinatorLayout -> {
                    startActivity(Intent(this@DesignSupportLibraryActivity, CoordinatorActivity::class.java))
                    closeDrawerLayoutIfOpen()
                }
                R.id.exit -> {
                    finish()
                }
            }
            true
        }
    }

    private fun setUpFragment(newFragment: Fragment, fragmentTag: String) {
        supportFragmentManager.commit {
            replace(R.id.selectedFragment, newFragment, fragmentTag)
        }
    }

    private fun closeDrawerLayoutIfOpen(): Boolean {
        if (binding.myDrawerLayout.isOpen) {
            binding.myDrawerLayout.close()
            return true
        }
        return false
    }

    override fun onBackPressed() {
        if (!closeDrawerLayoutIfOpen()) {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}