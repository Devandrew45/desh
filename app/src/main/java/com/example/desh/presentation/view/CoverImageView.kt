package com.example.desh.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.desh.databinding.LayoutChooseCoverViewBinding

class CoverImageView : RelativeLayout {

    private lateinit var binding: LayoutChooseCoverViewBinding

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = LayoutChooseCoverViewBinding.inflate(android.view.LayoutInflater.from(context), this, true)

    }

}