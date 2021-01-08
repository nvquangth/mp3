package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ModelItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LibraryItem(
    val id: Int,
    val title: String? = null,
    val iconResId: Int? = null,
    val totalItem: Int? = null
) : ModelItem(), Parcelable
