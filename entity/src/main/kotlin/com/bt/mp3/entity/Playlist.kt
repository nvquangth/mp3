package com.bt.mp3.entity

data class Playlist(
    val id: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val type: Int? = null,
    val publisher: Publisher? = null
) : Model()
