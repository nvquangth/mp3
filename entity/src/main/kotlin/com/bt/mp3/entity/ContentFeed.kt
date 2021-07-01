package com.bt.mp3.entity

data class ContentFeed(
    val type: String? = null,
    val layoutType: Int? = null,
    val photoSize: Int? = null,
    val photos: List<Photo>? = null,
    val thumbnail: String? = null,
    val mediaSource: MediaSource? = null
) : Model()
