/*
package com.example.desh.presentation.ui.root

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : Any, VH : RecyclerView.ViewHolder, VB : ViewBinding>(
    @IdRes val layoutId: Int,
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB

) : ListAdapter<T, VH>(BaseItemCallback<T>()) {

    var onItemClickListener: ((T) -> Unit)? = null
    fun setOnItemListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = getItem(holder.adapterPosition)
        bindView(item, holder, position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        val holder = setViewHolder(binding)
      //  onCreateBinding(holder)
//
        holder.itemView.setOnClickListener {
           // if (holder.adapterPosition != RecyclerView.NO_POSITION)
                //forItemClickListener?.invoke(holder.adapterPosition,getItem(holder.adapterPosition), it)
        }

       */
/* holder.itemView.setOnLongClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION)
               // onLongClickListener?.invoke(holder.adapterPosition, getItem(holder.adapterPosition), it)
            true
        }*//*

        return holder
    }

    abstract fun bindView(item: T, holder: VH, position: Int, itemCount: Int)

    @Suppress("UNCHECKED_CAST")
    private fun setViewHolder(binding: ViewBinding): VH = RecyclerView.ViewHolder(binding as VB)



    override fun getItemCount() = if (currentList.size == 0) 0 else currentList.size

    class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }


    class BaseViewHolder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    */
/*open class BaseViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: T) {
          //  binding.setVariable(BR.item, currentItem)


           *//*
*/
/* when (binding) {
                is ItemDateMatchesBinding -> {
                    binding.headToHeadRecyclerView.adapter = LeagueMatchesHeadToHeadAdapter()
                }

                is ItemLeagueFollowedWithMatchesBinding -> {
                    binding.matchesRecyclerView.adapter = MatchesAdapter()
                }

                is ItemTeamPlayersPositionsBinding -> {
                    binding.playersPositionRecycler.adapter = TeamPlayersAdapter()
                }

                is ItemStatisticsBlockBinding -> {
                    binding.recyclerBlock.adapter = SectionItemAdapter()
                }

                is ItemStandingBinding -> {
                    binding.formRecyclerView.adapter = StandingFormAdapter()
                }
            }*//*
*/
/*

        }
    }*//*

}*/
