package com.bt.mp3.util

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bt.base.extension.toPx
import com.bt.mp3.R
import com.bt.mp3.model.SongItem
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bindingArtistName")
fun TextView.setArtistNam(song: SongItem) {
    text = if (!song.artistsNames.isNullOrEmpty()) {
        song.artistsNames
    } else {
        val result = arrayListOf<String?>()
        song.artists?.forEach {
            result.add(it.name)
        }

        result.joinToString(", ")
    }
}

@BindingAdapter("bindingSrc")
fun ImageView.setImageRes(resId: Int?) {
    resId?.let {
        setImageResource(it)
    }
}

@BindingAdapter(value = ["bindingImageUrl", "bindingImageRadius", "bindingImageCircle"], requireAll = false)
fun ImageView.setImageUrl(url: String?, radius: Int?, isCircle: Boolean?) {

    val transforms = arrayListOf<Transformation<Bitmap>>()

    if (radius != null && radius > 0) {
        transforms.add(RoundedCorners(radius.toFloat().toPx(context)))
    }
    if (isCircle == true) {
        transforms.add(CircleCrop())
    }
    val options = RequestOptions().transform(*transforms.toTypedArray())

    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.bg_rect_primary_radius_4)
        .error(R.drawable.bg_rect_primary_radius_4)
        .apply(options)
        .into(this)
}
