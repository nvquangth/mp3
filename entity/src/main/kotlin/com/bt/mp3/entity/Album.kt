package com.bt.mp3.entity

data class Album(
    val id: String? = null,
    val pr: Boolean? = null,
    val artists: List<Artist>? = null,
    val artistsNames: String? = null,
    val isOfficial: Boolean? = null,
    val link: String? = null,
    val thumbnail: String? = null,
    val title: String? = null
) : Model()
