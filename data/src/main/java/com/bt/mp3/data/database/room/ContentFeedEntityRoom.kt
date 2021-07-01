package com.bt.mp3.data.database.room

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class ContentFeedEntityRoom(
    val type: Int? = null,
    @ColumnInfo(name = "layout_type")
    val layoutType: Int?,
    @ColumnInfo(name = "photo_size")
    val photoSize: Int? = null,
    @Embedded val photos: List<PhotoEntityRoom>? = null,
    val thumbnail: String? = null,
    @Embedded
    @ColumnInfo(name = "media_source")
    val mediaSource: MediaSourceEntityRoom? = null
)
