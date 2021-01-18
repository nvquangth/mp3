package com.bt.mp3.entity

data class PlaylistInfo(
    val items: List<Song>? = null,
    val total: Int? = null,
    val totalDuration: Long? = null
) : Model()
