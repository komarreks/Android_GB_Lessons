package com.example.cusomview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.cusomview.databinding.PostWindowViewBinding

class PostWindowView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var binding: PostWindowViewBinding

    init {
        val inflatedView = inflate(context,R.layout.post_window_view,this)
        binding = PostWindowViewBinding.bind(inflatedView)
    }

    fun setTopText(text: String){
        binding.topLine.text = text
    }

    fun setDownText(text: String){
        binding.downLine.text = text
    }
}