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
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/4/1/0/f/410f5f95073fdcc8682212e44dcb0c62.jpg"
        ),
        createPlaylistEntity(
            title = "Nữ Ca Sĩ Được Yêu Thích #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/1/e/a/e/1eae0f2f82f63a34febe5bac0f82c380.jpg"
        ),
        createPlaylistEntity(
            title = "Nam Ca Sĩ Được Yêu Thích",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/c/7/b/a/c7bafd36b3866539dfb5ff54556d6798.jpg"
        ),
        createPlaylistEntity(
            title = "Phát Hiện Của Năm #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/f/8/0/e/f80e61fa0a88eda33343f6102168d25d.jpg"
        ),
        createPlaylistEntity(
            title = "Ca Khúc Dance/Electronic Được Yêu Thích #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/a/0/9/8/a098d2e075c1f203a235779a3c8a9a5d.jpg"
        ),
        createPlaylistEntity(
            title = "Ca Khúc R&B/Soul Được Yêu Thích #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/0/e/9/6/0e96af34e48f6adc9429aee6af775751.jpg"
        ),
        createPlaylistEntity(
            title = "Ca Khúc Pop/Ballab Được Yêu Thích #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/a/c/a/8/aca854deffa27ee8129d791490432dc4.jpg"
        ),
        createPlaylistEntity(
            title = "Ca Khúc Rap/Hip-Hop Được Yêu Thích #ZMA2020",
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/1/5/1/b/151b44dcf0571db103d8ed56744ea267.jpg"
        )
    )
}
