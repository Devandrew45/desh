package com.example.desh.presentation.view.socialdropdown

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desh.R
import com.example.desh.databinding.ItemSpinnersSignupBinding

class SocialMediaSpinnerView : LinearLayout {

    private lateinit var binding: ItemSpinnersSignupBinding
    private lateinit var socialListAdapter: SocialItemsListAdapter

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ItemSpinnersSignupBinding.inflate(LayoutInflater.from(context), this, true)
        socialListAdapter = SocialItemsListAdapter()
        initView()
    }

    private fun initView() {
        binding.apply {

            socialList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            socialList.adapter = socialListAdapter

            arrowBtn.setOnClickListener {
                if (!socialList.isVisible) {
                    arrowBtn.setImageDrawable(getDrawable(context, R.drawable.arrow_up))
                    socialList.isVisible = true
                } else {
                    arrowBtn.setImageDrawable(getDrawable(context, R.drawable.arrow_down))
                    socialList.isVisible = false
                }
            }

           /* socialListAdapter.setOnItemListener {
                ImageViewEngine(socialSelctedIcon).setImage(it.icon.toString())
            }*/
        }

    }

    fun setSocialList(list: List<SocialItem>) {
        if (::socialListAdapter.isInitialized) {
            socialListAdapter.submitList(list)
        }
    }


}