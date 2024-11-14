package com.example.movielist.utils

import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.utils.Constants.IMAGE_URL
import com.makeramen.roundedimageview.RoundedImageView

fun RoundedImageView.loadImage(imgUri: String) {
    Glide
        .with(context)
        .load(IMAGE_URL.plus(imgUri))
        .placeholder(R.drawable.loading)
        .error(R.drawable.error)
        .into(this)
}