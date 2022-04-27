package com.example.kotlinbasics.android_permissions

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbasics.R

class PermissionAdapter(
    private val context: Context,
    private val permissionsList: MutableList<String>,
    private var permissionStatus: MutableList<Boolean>
) : RecyclerView.Adapter<PermissionAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPermissionName: TextView = view.findViewById(R.id.tvPermissionName)
        val tvPermissionStatus: ImageView = view.findViewById(R.id.tvPermissionStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.permission_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvPermissionName.text = permissionsList[position]
            tvPermissionStatus.clipToOutline = true
            if (permissionStatus[position]) {
                tvPermissionStatus.setImageResource(android.R.color.holo_green_light)
            } else {
                tvPermissionStatus.setImageResource(android.R.color.holo_red_light)
            }
        }
    }

    override fun getItemCount(): Int {
        return permissionsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePermissionStatus(newPermissionStatus: MutableList<Boolean>) {
        permissionStatus = newPermissionStatus
        notifyDataSetChanged()
    }
}