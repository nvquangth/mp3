package com.bt.mp3.model

enum class PlaylistTypeItem(val value: Int) {
    MOST_POPULAR(1),
    TOP10(2),
    TOP50(3),
    TOP100(4)
}

object PlaylistTypeItemMapper {

    fun mapToPresentation(value: Int?): PlaylistTypeItem = when (value) {
        PlaylistTypeItem.TOP10.value -> PlaylistTypeItem.TOP10
        PlaylistTypeItem.TOP50.value -> PlaylistTypeItem.TOP50
        PlaylistTypeItem.TOP100.value -> PlaylistTypeItem.TOP100
        else -> PlaylistTypeItem.MOST_POPULAR
    }

    fun mapToDomain(type: PlaylistTypeItem?): Int? = type?.value
}
