package com.bt.mp3.entity

data class Artist(
    val id: String? = null,
    val name: String? = null,
    val link: String? = null,
    val spotlight: Boolean? = null,
    val cover: String? = null,
    val thumbnail: String? = null,
    val playlistId: String? = null,
    val totalFollow: String? = null
) : Model()
