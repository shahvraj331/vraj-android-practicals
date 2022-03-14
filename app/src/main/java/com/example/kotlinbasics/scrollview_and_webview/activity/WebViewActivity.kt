package com.example.kotlinbasics.scrollview_and_webview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityWebViewBinding
import androidx.appcompat.widget.SearchView

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.scrollview)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(getString(R.string.google_url))

        binding.svQueryToSearch.apply {
            queryHint = getString(R.string.search)
            setIconifiedByDefault(false)
        }

        binding.svQueryToSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val currentText = query ?: getString(R.string.google_url)
                binding.webView.loadUrl(getURL(currentText))
                binding.svQueryToSearch.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.btnLoadPDF.setOnClickListener {
            binding.webView.loadUrl(getString(R.string.pdf_embed_url, getString(R.string.pdf_url)))
        }
    }

    private fun getURL(text: String): String {
        return if (text.startsWith(getString(R.string.http)) && text.endsWith(getString(R.string.com))) {
            text
        } else if (text.startsWith(getString(R.string.www)) && text.endsWith(getString(R.string.com))) {
            getString(R.string.http_prefix_url, text)
        } else {
            getString(R.string.google_search_url, text)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            binding.progressBar.visibility = View.VISIBLE
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }
}