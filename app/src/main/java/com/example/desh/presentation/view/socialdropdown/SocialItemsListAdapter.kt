package com.example.desh.presentation.view.socialdropdown

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desh.databinding.ItemSocialDropDownListBinding

@SuppressLint("ResourceType")
class SocialItemsListAdapter :
    ListAdapter<SocialItem, SocialItemsListAdapter.SocialItemViewHolder>(SocialItemsDiffCallBack()) {
    inner class SocialItemViewHolder(private var binding: ItemSocialDropDownListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(socialItem: SocialItem) {
            binding.apply {

            }
        }
    }

    class SocialItemsDiffCallBack : DiffUtil.ItemCallback<SocialItem>() {
        override fun areItemsTheSame(oldItem: SocialItem, newItem: SocialItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: SocialItem, newItem: SocialItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SocialItemViewHolder(
            ItemSocialDropDownListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = currentList.size


    override fun onBindViewHolder(holder: SocialItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

