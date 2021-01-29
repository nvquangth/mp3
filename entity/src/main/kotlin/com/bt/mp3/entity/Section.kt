package com.bt.mp3.entity

data class Section(
    val items: List<Song>? = null,
    val link: String? = null,
    val sectionId: String? = null,
    val sectionType: String? = null,
    val title: String? = null,
    val viewType: String? = null,
    val chart: Chart? = null,
    val chartType: String? = null
) : Model()
