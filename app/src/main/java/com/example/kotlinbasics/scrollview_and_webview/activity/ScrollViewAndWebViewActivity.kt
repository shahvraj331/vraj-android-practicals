package com.example.kotlinbasics.scrollview_and_webview.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityScrollViewAndWebViewBinding

class ScrollViewAndWebViewActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityScrollViewAndWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollViewAndWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.scrollview_and_webview)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSearchView.setOnClickListener {
            intentTo(SearchViewActivity::class.java)
        }

        binding.btnWebView.setOnClickListener {
            intentTo(WebViewActivity::class.java)
        }

        binding.btnScrollView.setOnClickListener {
            intentTo(ScrollViewActivity::class.java)
        }
    }

    private fun intentTo(destinationActivity: Class<*>) {
        startActivity(Intent(this@ScrollViewAndWebViewActivity, destinationActivity))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}