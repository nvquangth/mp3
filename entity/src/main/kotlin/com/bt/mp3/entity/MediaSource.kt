package com.bt.mp3.entity

data class MediaSource(
    val s360: String? = null,
    val s480: String? = null,
    val s720: String? = null,
    val ratio: Float? = null,
    val thumbnail: String? = null
) : Model()
