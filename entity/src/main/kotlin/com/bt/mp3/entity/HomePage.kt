package com.bt.mp3.entity

data class HomePage(
    val sections: List<Section>? = null,
    val hasMore: Boolean? = null,
    val total: Int? = null
) : Model()
