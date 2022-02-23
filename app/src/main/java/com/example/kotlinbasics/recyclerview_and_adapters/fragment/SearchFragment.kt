package com.example.kotlinbasics.recyclerview_and_adapters.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.recyclerview_and_adapters.MarvelMoviesModel
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.MarvelMovieAdapter

class SearchFragment : Fragment() {

    private var allMoviesData = MarvelMoviesModel.getMarvelMovies()
    private lateinit var isExpandedArray: BooleanArray

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val searchField = view.findViewById<EditText>(R.id.etSearch)
        val moviesList = view.findViewById<RecyclerView>(R.id.rvMarvelMovies)
        val emptyView = view.findViewById<TextView>(R.id.tvEmptyListText)
        isExpandedArray = BooleanArray(allMoviesData.size)
        moviesList.adapter = updateList(allMoviesData)

        searchField.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newMoviesData = allMoviesData.filter {
                    it.name.startsWith(searchField.text.toString(), true)
                }
                isExpandedArray = BooleanArray(newMoviesData.size)
                moviesList.adapter = updateList(newMoviesData as ArrayList<MarvelMoviesModel>)
                if (newMoviesData.isEmpty()) {
                    emptyView.visibility = View.VISIBLE
                } else {
                    emptyView.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {  }

        })
        return view
    }

    private fun updateList(allMoviesData: ArrayList<MarvelMoviesModel>): MarvelMovieAdapter? {
        return context?.applicationContext?.let { MarvelMovieAdapter(it, allMoviesData, isExpandedArray) }
    }
}