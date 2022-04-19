package com.example.kotlinbasics.android_architecture.mvvm_architecture

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(imageUrl: String) {
    Picasso.get().load(imageUrl).into(this)
}

@BindingAdapter("setText")
fun TextView.updateText(text: String) {
    this.text = text
}

@InverseBindingAdapter(attribute = "setText")
fun TextView.getLatestText(): String {
    return this.text.toString()
}