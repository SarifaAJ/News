package com.example.tamasya.extention

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.tamasya.helper.Config.BASE_URL

fun ImageView.loadImage(path: String) {
    val url = BASE_URL + path
    Glide.with(context)
        .load(url)
        .into(this)
}