package com.example.desh.domain.utils

import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageViewEngine(@NonNull private var view: ImageView) {

    fun setImage(source: String) {
        Glide.with(view)
            .load(source)
            .transition(DrawableTransitionOptions().crossFade())
            .into(view)

    }
}