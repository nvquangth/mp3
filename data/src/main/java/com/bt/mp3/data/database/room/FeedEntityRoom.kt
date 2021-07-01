package com.bt.mp3.data.database.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FeedEntityRoom(
    @PrimaryKey val id: String,
    val artistId: String,
    @Embedded
    val content: ContentFeedEntityRoom? = null,
    val type: Int? = null,
    val title: String? = null,
    @ColumnInfo(name = "created_time")
    val createdTime: Long? = null,
    @ColumnInfo(name = "publish_time")
    val publishedTime: Long? = null,
    @ColumnInfo(name = "short_description")
    val shortDescription: String? = null,
    val description: String? = null,
    @ColumnInfo(name = "source_type")
    val sourceType: Int? = null,
    val status: String? = null,
    val like: Long? = null,
    val comment: Long? = null
)
