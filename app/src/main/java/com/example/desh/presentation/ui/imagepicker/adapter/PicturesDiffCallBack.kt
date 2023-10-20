package com.example.desh.presentation.ui.imagepicker.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.desh.mediaproviders.pictures.model.PictureContent

class PicturesDiffCallBack : DiffUtil.ItemCallback<PictureContent>() {
    override fun areItemsTheSame(oldItem: PictureContent, newItem: PictureContent): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PictureContent, newItem: PictureContent): Boolean =
        oldItem == newItem
}
