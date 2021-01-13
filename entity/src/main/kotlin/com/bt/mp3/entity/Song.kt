package com.bt.mp3.entity

data class Song(
    val id: String? = null,
    val title: String? = null,
    val artists: List<Artist>? = null,
    val publisher: Publisher? = null,
    val streamUrl: String? = null,
    val imageUrl: String? = null
) : Model()
