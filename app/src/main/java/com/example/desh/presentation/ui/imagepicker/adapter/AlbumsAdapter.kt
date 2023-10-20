package com.example.desh.presentation.ui.imagepicker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.desh.R
import com.example.desh.databinding.ItemSpennirTextBinding
import com.example.desh.mediaproviders.pictures.model.AlbumItem

internal class AlbumsAdapter(albumItems: List<AlbumItem>, context: Context) : ArrayAdapter<AlbumItem>(context, R.layout.item_spennir_text, albumItems) {

    override fun getDropDownView(position: Int, view: View?, parent: ViewGroup): View {
        val holderView = ItemSpennirTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val item = getItem(position)
        if (item != null) {
            holderView.text.text = item.name
        }
     /*   var convertView = view
        val viewHolder: ViewHolderDrop
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            viewHolder = ViewHolderDrop(convertView)
            convertView.tag = viewHolder

        } else {
            viewHolder = convertView.tag as ViewHolderDrop
        }*/

        return holderView.root
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val holderView = ItemSpennirTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val item = getItem(position)
        if (item != null) {
            holderView.text.text = item.name
        }
        /*   var convertView = view
           val viewHolder: ViewHolderDrop
           if (convertView == null) {
               convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
               viewHolder = ViewHolderDrop(convertView)
               convertView.tag = viewHolder

           } else {
               viewHolder = convertView.tag as ViewHolderDrop
           }*/

        return holderView.root
    }
}