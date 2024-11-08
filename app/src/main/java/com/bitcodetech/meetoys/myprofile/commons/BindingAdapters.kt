package com.bitcodetech.findroomies.myprofile.commons

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bitcodetech.meetoys.R
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun setImageUrlToImageView(imageView: ImageView, imageUrl : String) {
    Glide.with(imageView)
        .load(imageUrl)
        .error(R.mipmap.ic_launcher)
        .into(imageView)
}