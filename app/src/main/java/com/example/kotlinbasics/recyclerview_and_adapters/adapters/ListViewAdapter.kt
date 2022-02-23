package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinbasics.R

class ListViewAdapter(private val context: Context, private val names: MutableList<String>, private val contacts: MutableList<String>): BaseAdapter() {

    class ViewHolder(view: View?) {
        val contactPersonImage: ImageView? = view?.findViewById(R.id.ivContactImage)
        val contactPersonName: TextView? = view?.findViewById(R.id.tvContactPersonName)
        val contactNumber: TextView? = view?.findViewById(R.id.tvContactNumber)
    }

    override fun getCount(): Int {
        return names.count()
    }

    override fun getItem(position: Int): Any {
        return names[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentView = LayoutInflater.from(context).inflate(R.layout.contacts_listview, parent, false)
        val viewHolder = ViewHolder(currentView)
        viewHolder.apply {
            contactPersonImage?.setImageResource(R.drawable.contact_person_image)
            contactPersonName?.text = names[position]
            contactNumber?.text = contacts[position]
        }

        return currentView
    }
}