package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.recyclerview_and_adapters.MarvelMoviesModel

class MarvelMovieAdapter(private val context: Context, private val moviesList: ArrayList<MarvelMoviesModel>,
    private val isExpandedArray: BooleanArray): RecyclerView.Adapter<MarvelMovieAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageOfMovie: ImageView = view.findViewById(R.id.ivMovieImage)
        val nameOfMovie: TextView = view.findViewById(R.id.tvNameOfMovie)
        val actorName: TextView = view.findViewById(R.id.tvActorName)
        val currentItem: ConstraintLayout = view.findViewById(R.id.clMovieItem)
        val otherDetails: ConstraintLayout = view.findViewById(R.id.clDetailsOfMovie)
        val teamOfMovie: TextView = view.findViewById(R.id.tvTeam)
        val createdBy: TextView = view.findViewById(R.id.tvCreatedBy)
        val publishedBy: TextView = view.findViewById(R.id.tvPublisher)
        val detailsOfMovie: TextView = view.findViewById(R.id.tvDetailsOfMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val currentView = LayoutInflater.from(context).inflate(R.layout.marvel_movie_item, parent, false)
        return ViewHolder(currentView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isExpandedArray[position]) {
            holder.otherDetails.visibility = View.VISIBLE
        } else {
            holder.otherDetails.visibility = View.GONE
        }
        val imageResource = context.resources.getIdentifier(moviesList[position].image, null, context.packageName)
        holder.apply {
            imageOfMovie.setImageResource(imageResource)
            nameOfMovie.text = context.getString(R.string.name_and_first_appearance, moviesList[position].name, moviesList[position].firstAppearance)
            actorName.text = moviesList[position].realName
            teamOfMovie.text = context.getString(R.string.team, moviesList[position].team)
            createdBy.text = context.getString(R.string.created_by, moviesList[position].createdBy)
            publishedBy.text = context.getString(R.string.publisher, moviesList[position].publisher)
            detailsOfMovie.text = context.getString(R.string.details_movie, moviesList[position].bio)
            currentItem.setOnClickListener {
                for (index in isExpandedArray.indices) {
                    if (index == position) {
                        isExpandedArray[index] = !isExpandedArray[position]
                    } else {
                        isExpandedArray[index] = false
                    }
                }
                if (otherDetails.visibility == View.GONE) {
                    otherDetails.visibility = View.VISIBLE
                } else {
                    holder.otherDetails.visibility = View.GONE
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return moviesList.count()
    }
}