package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlinbasics.R
import com.example.kotlinbasics.recyclerview_and_adapters.MarvelMoviesModel

class MoviesListAdapter(private val context: Context, private val moviesList: ArrayList<MarvelMoviesModel>,
    private val isExpandedArray: BooleanArray): BaseAdapter() {
    class ViewHolder(view: View) {
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
    override fun getCount(): Int {
        return moviesList.count()
    }

    override fun getItem(position: Int): Any {
        return moviesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentView = LayoutInflater.from(context).inflate(R.layout.marvel_movie_item, parent, false)
        val holder = ViewHolder(currentView)
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
            }
        }
        notifyDataSetChanged()

        return currentView
    }
}