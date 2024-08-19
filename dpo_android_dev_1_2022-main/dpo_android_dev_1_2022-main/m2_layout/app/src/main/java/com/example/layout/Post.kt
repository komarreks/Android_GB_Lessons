package com.example.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.layout.databinding.PostBinding

class Post
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = PostBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setTopText(text: String) {
        binding.topText.text = text
    }

    fun setBottomText(text: String) {
        binding.bottomText.text = text
    }
}