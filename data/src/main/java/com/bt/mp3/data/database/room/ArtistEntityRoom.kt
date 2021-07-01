package com.bt.mp3.data.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtistEntityRoom(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val link: String? = null,
    val spotlight: Boolean? = null,
    val cover: String? = null,
    val thumbnail: String? = null,
    @ColumnInfo(name = "total_follow")
    val totalFollow: String? = null
)