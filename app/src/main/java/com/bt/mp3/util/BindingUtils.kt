package com.bt.mp3.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bt.base.extension.toPx
import com.bt.mp3.R
import com.bt.mp3.model.PlaylistTypeItem
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bindingSrc")
fun ImageView.setImageRes(resId: Int?) {
    resId?.let {
        setImageResource(it)
    }
}

@BindingAdapter("bindingPlaylistTitle")
fun TextView.setText(type: PlaylistTypeItem?) {
    text = when (type) {
        PlaylistTypeItem.MOST_POPULAR -> context.getString(R.string.most_popular)
        PlaylistTypeItem.TOP10 -> context.getString(R.string.top_10)
        PlaylistTypeItem.TOP50 -> context.getString(R.string.top_50)
        PlaylistTypeItem.TOP100 -> context.getString(R.string.top_100)
        else -> ""
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
