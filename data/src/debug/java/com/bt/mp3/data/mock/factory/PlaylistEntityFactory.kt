package com.bt.mp3.data.mock.factory

import com.bt.mp3.data.model.PlaylistEntity
import com.bt.mp3.data.model.PublisherEntity
import java.util.UUID

object PlaylistEntityFactory {

    fun createPlaylistEntity(
        title: String? = null,
        imageUrl: String? = null,
        type: Int? = null,
        publisher: PublisherEntity? = null
    ): PlaylistEntity = PlaylistEntity(
        id = UUID.randomUUID().toString(),
        title = title?.let { it } ?: "Quán Quân #ZMA2020",
        imageUrl = imageUrl?.let { it } ?: "",
        type = type?.let { it } ?: 1,
        publisher = publisher?.let { it } ?: PublisherEntityFactory.createPublisherEntity()
    )

    fun createPlaylistEntities(): List<PlaylistEntity> = listOf(
        createPlaylistEntity(
            title = "Quán Quân #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/7/0/5/0/7050e880b3650d93b983d2ff3efa29c9.jpg"
        ),
        createPlaylistEntity(
            title = "Nghệ Sĩ Của Năm #ZMA2020",
        ),
        createPlaylistEntity(
            title = "Nữ Ca Sĩ Được Yêu Thích #ZMA2020",
        ),
        createPlaylistEntity(
            title = "Nam Ca Sĩ Được Yêu Thích",
        ),
        createPlaylistEntity(
            title = "Phát Hiện Của Năm #ZMA2020",
        ),
        createPlaylistEntity(
            title = "Ca Khúc Dance/Electronic Được Yêu Thích #ZMA2020",
        ),
        createPlaylistEntity(
            title = "Ca Khúc R&B/Soul Được Yêu Thích #ZMA2020",
        ),
        createPlaylistEntity(
            title = "Ca Khúc Pop/Ballab Được Yêu Thích #ZMA2020",
        ),
        createPlaylistEntity(
            title = "Ca Khúc Rap/Hip-Hop Được Yêu Thích #ZMA2020",
        )
    )
}
