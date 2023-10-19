package com.example.desh.presentation.ui.imagepicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.desh.databinding.ItemImagePickerBinding
import com.example.desh.mediaproviders.module.PictureContent

class ImagesAdapter : ListAdapter<PictureContent, ImagesAdapter.ImageViewHolder>(PicturesDiffCallBack()) {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1
    var onPictureClickListener: ((String) -> Unit)? = null
    fun setOnPictureListener(listener: (String) -> Unit) {
        onPictureClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImagePickerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ImageViewHolder(private val binding: ItemImagePickerBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(pictureContent: PictureContent) {
            binding.apply {
                Glide.with(imageHolder)
                    .load(pictureContent.picturePath)
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(imageHolder)

             /*   checkear.isChecked = pictureContent.isSelected!!
                checkear.setOnCheckedChangeListener {_, isChecked ->
                    pictureContent.isSelected = isChecked
                    checkear.isChecked = isChecked

                    if (isChecked) {
                        card.strokeWidth = 4
                    } else {
                        card.strokeWidth = 0
                    }
                }*/




              /*  selectedItemPos = adapterPosition
                lastItemSelectedPos = if(lastItemSelectedPos == -1)
                    selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
*/


            }
        }
    }

    fun defaultItem(){

    }

    fun selectedItem(){

    }

    override fun getItemCount(): Int {
        return currentList.size
    }


}