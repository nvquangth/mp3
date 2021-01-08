package com.bt.mp3.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindingSrc")
fun ImageView.setImageRes(resId: Int?) {
    resId?.let {
        setImageResource(it)
    }
}
