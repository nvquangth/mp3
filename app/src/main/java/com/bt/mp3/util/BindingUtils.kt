package com.bt.mp3.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bt.base.extension.toPx
import com.bt.mp3.R
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bindingSrc")
fun ImageView.setImageRes(resId: Int?) {
    resId?.let {
        setImageResource(it)
    }
}

@BindingAdapter(value = ["bindingImageUrl", "bindingImageRadius"], requireAll = false)
fun ImageView.setImageUrl(url: String?, radius: Int?) {

    val options = RequestOptions().transform(CenterCrop(), RoundedCorners(radius?.toFloat()?.toPx(context) ?: 0))

    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.bg_rect_primary_4)
        .error(R.drawable.bg_rect_primary_4)
        .apply(options)
        .into(this)
}
