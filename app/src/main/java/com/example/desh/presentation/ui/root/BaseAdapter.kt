package com.example.desh.presentation.ui.root

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder, VB : ViewBinding>(
    val viewHolder: (binding: VB) -> VH,

    val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,

    areItemsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    areContentsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    private val onCreateBinding: (holder: VH) -> Unit = {}

) : ListAdapter<T, VH>(GenericDiffUtil(areItemsTheSameCallback, areContentsTheSameCallback)) {
    abstract fun bindItems(item: T, holder: VH, position: Int, itemCount: Int)

    var forItemClickListener: ((position: Int, item: T, view: View) -> Unit)? = null
    var onLongClickListener: ((position: Int, item: T, view: View) -> Unit)? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = getItem(holder.adapterPosition)
        bindItems(item, holder, position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        val holder = setViewHolder(binding)
        onCreateBinding(holder)

        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION)
                forItemClickListener?.invoke(holder.adapterPosition,getItem(holder.adapterPosition), it)
        }

        holder.itemView.setOnLongClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION)
                onLongClickListener?.invoke(holder.adapterPosition, getItem(holder.adapterPosition), it)
            true
        }
        return holder
    }

    @Suppress("UNCHECKED_CAST")
    private fun setViewHolder(binding: ViewBinding): VH = viewHolder(binding as VB)


    class GenericDiffUtil<T>(
        private val areItemsTheSameCallback: (old: T, new: T) -> Boolean?,
        private val areContentsTheSameCallback: (old: T, new: T) -> Boolean?
    ) : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any) =
            (areItemsTheSameCallback(oldItem, newItem) ?: newItem) == oldItem


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any) =
            (areContentsTheSameCallback(oldItem, newItem) ?: newItem) == oldItem


    }

    fun <T> diffUtilDSL(
        areItemsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
        areContentsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null }
    ) = GenericDiffUtil(areItemsTheSameCallback, areContentsTheSameCallback)

}