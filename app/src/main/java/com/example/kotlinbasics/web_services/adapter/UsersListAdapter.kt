package com.example.kotlinbasics.web_services.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.web_services.data_classes.UsersList
import com.example.kotlinbasics.web_services.interfaces.UsersListInterface
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class UsersListAdapter(
    private val context: Context,
    private val usersList: ArrayList<UsersList>,
    private val onClickInterface: UsersListInterface
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageOfUser: ImageView = view.findViewById(R.id.ivUserImage)
        val firstNameOfUser: TextView = view.findViewById(R.id.etFirstNameOfUser)
        val emailOfUser: TextView = view.findViewById(R.id.etEmailOfUser)
        val userItem: ConstraintLayout = view.findViewById(R.id.clUserItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            imageOfUser.clipToOutline = true
            Picasso.get().load(usersList[position].avatar).into(imageOfUser)
            firstNameOfUser.text = usersList[position].firstName
            emailOfUser.text = usersList[position].email
            userItem.setOnClickListener {
                usersList[position].id?.let { userID ->
                    onClickInterface.onUserClicked(userID)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return usersList.count()
    }
}