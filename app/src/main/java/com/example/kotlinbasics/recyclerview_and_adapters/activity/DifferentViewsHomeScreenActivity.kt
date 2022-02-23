package com.example.kotlinbasics.recyclerview_and_adapters.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.MainActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityDiffViewsHomeScreenBinding

class DifferentViewsHomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiffViewsHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiffViewsHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.diff_views)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnListView.setOnClickListener {
            intentTo(ListViewActivity::class.java)
        }

        binding.btnRecyclerView.setOnClickListener {
            intentTo(RecyclerViewActivity::class.java)
        }

        binding.btnGridLayout.setOnClickListener {
            intentTo(GridViewActivity::class.java)
        }

        binding.btnViewPager.setOnClickListener {
            intentTo(ViewPagerActivity::class.java)
        }

        binding.btnBottomNavigation.setOnClickListener {
            intentTo(BottomNavViewPagerActivity::class.java)
        }
    }

    private fun intentTo(destinationActivity: Class<*>) {
        startActivity(Intent(this@DifferentViewsHomeScreenActivity, destinationActivity))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        return true
    }
}