package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Artist
import javax.inject.Inject

data class ArtistEntity(
    val id: String? = null,
    val name: String? = null,
    val link: String? = null,
    val spotlight: Boolean? = null,
    val cover: String? = null,
    val thumbnail: String? = null,
    val playlistId: String? = null,
    val totalFollow: String? = null
) : ModelEntity()

class ArtistEntityMapper @Inject constructor() : EntityMapper<Artist, ArtistEntity> {
    override fun mapToData(model: Artist): ArtistEntity = ArtistEntity(
        id = model.id,
        name = model.name,
        link = model.link,
        spotlight = model.spotlight,
        cover = model.cover,
        thumbnail = model.thumbnail,
        playlistId = model.playlistId,
        totalFollow = model.totalFollow
    )

    override fun mapToDomain(entity: ArtistEntity): Artist = Artist(
        id = entity.id,
        name = entity.name,
        link = entity.link,
        spotlight = entity.spotlight,
        cover = entity.cover,
        thumbnail = entity.thumbnail,
        playlistId = entity.playlistId,
        totalFollow = entity.totalFollow
    )
}
