package com.example.desh.presntation.dialog.imagepicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.desh.databinding.ItemImagePickerBinding
import com.example.desh.mediaproviders.pictures.module.PictureContent

class ImagesAdapter : ListAdapter<PictureContent, ImagesAdapter.ImageViewHolder>(
    PicturesDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImagePickerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ImageViewHolder(private val binding: ItemImagePickerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pictureContent: PictureContent){
            binding.apply {
                Glide.with(imageHolder)
                    .load(pictureContent.picturePath)
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(imageHolder)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


}