package com.example.kotlinbasics.scrollview_and_webview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivitySearchViewBinding
import com.example.kotlinbasics.recyclerview_and_adapters.MarvelMoviesModel
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.MarvelMovieAdapter

class SearchViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchViewBinding
    private var allMoviesData = MarvelMoviesModel.getMarvelMovies()
    private lateinit var isExpandedArray: BooleanArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.searchview)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        isExpandedArray = BooleanArray(allMoviesData.size)

        binding.rvMoviesListVertical.adapter = updateList(allMoviesData)
        binding.rvMoviesListHorizontal.adapter = updateList(allMoviesData)

        binding.svMovieToSearch.apply {
            queryHint = getString(R.string.search)
            setIconifiedByDefault(false)
        }

        binding.svMovieToSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val currentText = newText ?: ""
                val newMoviesData = allMoviesData.filter {
                    it.name.contains(currentText, true)
                }
                isExpandedArray = BooleanArray(newMoviesData.size)
                binding.rvMoviesListHorizontal.adapter = updateList(newMoviesData as ArrayList<MarvelMoviesModel>)
                binding.rvMoviesListVertical.adapter = updateList(newMoviesData)
                binding.tvEmptyList.visibility = if (newMoviesData.isEmpty()) View.VISIBLE else View.INVISIBLE
                return true
            }
        })
    }

    private fun updateList(allMoviesData: ArrayList<MarvelMoviesModel>): MarvelMovieAdapter {
        return MarvelMovieAdapter(this@SearchViewActivity, allMoviesData, isExpandedArray)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}