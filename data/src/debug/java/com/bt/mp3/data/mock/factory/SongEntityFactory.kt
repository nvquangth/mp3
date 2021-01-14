package com.bt.mp3.data.mock.factory

import com.bt.mp3.data.model.ArtistEntity
import com.bt.mp3.data.model.PublisherEntity
import com.bt.mp3.data.model.SongEntity
import java.util.UUID

object SongEntityFactory {

    fun createSongEntity(
        title: String? = null,
        artists: List<ArtistEntity>? = null,
        publisher: PublisherEntity? = null,
        streamUrl: String? = null,
        imageUrl: String? = null
    ): SongEntity = SongEntity(
        id = UUID.randomUUID().toString(),
        title = title?.let { it } ?: "Học Cách Đi Một Mình",
        artists = artists?.let { it } ?: ArtistEntityFactory.createArtistEntities(),
        publisher = publisher?.let { it } ?: PublisherEntityFactory.createPublisherEntity("Lương Bích Hữu"),
        streamUrl = streamUrl?.let { it } ?: "",
        imageUrl = imageUrl?.let { it } ?: ""
    )

    fun createSongEntities(): List<SongEntity> = listOf(
        createSongEntity(
            title = "Học Cách Đi Một Mình",
            artists = ArtistEntityFactory.createArtistEntities("Lương Bích Hữu"),
            publisher = PublisherEntityFactory.createPublisherEntity("Nam Việt Music"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/covers/7/f/7f369343b2e4d506e9aa0099adf705c8_1362240688.jpg"
        ),
        createSongEntity(
            title = "Sóng Gió",
            artists = ArtistEntityFactory.createArtistEntities("Jack", "K-ICM"),
            publisher = PublisherEntityFactory.createPublisherEntity("K-ICM"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/8/3/6/c/836cf31f036fb8f89b78cfd07cd77477.jpg"
        ),
        createSongEntity(
            title = "Kiếp Ve Sầu",
            artists = ArtistEntityFactory.createArtistEntities("Đan Trường"),
            publisher = PublisherEntityFactory.createPublisherEntity("Đan Trường"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/covers/8/9/89af1855ce349828e851a9fe834afc80_1286475601.jpg"
        ),
        createSongEntity(
            title = "Tình Yêu Mang Theo",
            artists = ArtistEntityFactory.createArtistEntities("Nhật Tinh Anh", "Khánh Ngọc"),
            publisher = PublisherEntityFactory.createPublisherEntity("Nhạc Xanh GMC"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/covers/f/3/f3ccdd27d2000e3f9255a7e3e2c48800_1299730546.jpg"
        ),
        createSongEntity(
            title = "Nỗi Đau Xót Xa",
            artists = ArtistEntityFactory.createArtistEntities("Minh Vương"),
            publisher = PublisherEntityFactory.createPublisherEntity("Minh Vương M4U"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/covers/f/d/fdae18d20fe86a20cb7cf45e96fcb88d_1313463756.jpg"
        ),
        createSongEntity(
            title = "Đừng Như Thói Quen",
            artists = ArtistEntityFactory.createArtistEntities("JayKii", " Sara Lưu"),
            publisher = PublisherEntityFactory.createPublisherEntity("JayKii"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/0/8/5/3/0853118a8d14808d8526bc717409ac90.jpg"
        ),
        createSongEntity(
            title = "Yêu Là Tha Thu (Em Chưa 18 OST)",
            artists = ArtistEntityFactory.createArtistEntities("Only C"),
            publisher = PublisherEntityFactory.createPublisherEntity("Only C"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/covers/c/c/ccba0ba0430c375e16e95309cd534c09_1492130923.jpg"
        ),
        createSongEntity(
            title = "Hơn Cả Yêu",
            artists = ArtistEntityFactory.createArtistEntities("Đức Phúc"),
            publisher = PublisherEntityFactory.createPublisherEntity("Đức Phúc"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/a/9/e/d/a9ed142c215560ab45f6b2b433907f90.jpg"
        ),
        createSongEntity(
            title = "Thích Thì Đến",
            artists = ArtistEntityFactory.createArtistEntities("Lê Bảo Bình"),
            publisher = PublisherEntityFactory.createPublisherEntity("Lê Bảo Bình"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/b/4/4/6/b4461d303cba114b38429c6ea84d9fa2.jpg"
        ),
        createSongEntity(
            title = "Đi Cùng Em",
            artists = ArtistEntityFactory.createArtistEntities("Minh Vương M4U", "Lemon Climb", "ACV"),
            publisher = PublisherEntityFactory.createPublisherEntity("ACV Music"),
            imageUrl = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/f/b/f/3/fbf38164222f12d85a41e7631b944b20.jpg"
        )
    )
}
