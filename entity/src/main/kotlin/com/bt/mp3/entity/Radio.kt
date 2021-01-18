package com.bt.mp3.entity

data class Radio(
    val id: String? = null,
    val pr: Boolean? = null,
    val artists: List<Artist>? = null,
    val artistsNames: String? = null,
    val isAlbum: Boolean? = null,
    val isShuffle: Boolean? = null,
    val isSingle: Boolean? = null,
    val isOfficial: Boolean? = null,
    val link: String? = null,
    val playItemMode: Int? = null,
    val subType: Int? = null,
    val textType: String? = null,
    val thumbnail: String? = null,
    val thumbnailM: String? = null,
    val title: String? = null,
    val uid: Int? = null,
    val userName: String? = null
) : Model()
