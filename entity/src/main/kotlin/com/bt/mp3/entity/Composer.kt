package com.bt.mp3.entity

data class Composer(
    val id: String? = null,
    val cover: String? = null,
    val link: String? = null,
    val name: String? = null,
    val playlistId: String? = null,
    val spotlight: Boolean? = null,
    val thumbnail: String? = null,
    val totalFollow: Int? = null
) : Model()
