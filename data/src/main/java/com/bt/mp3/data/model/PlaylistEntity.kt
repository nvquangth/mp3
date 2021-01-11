package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Playlist
import javax.inject.Inject

data class PlaylistEntity(
    val id: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val type: Int? = null,
    val publisher: PublisherEntity? = null
) : ModelEntity()

class PlaylistEntityMapper @Inject constructor(
    private val publisherEntityMapper: PublisherEntityMapper
) : EntityMapper<Playlist, PlaylistEntity> {

    override fun mapToData(model: Playlist): PlaylistEntity = PlaylistEntity(
        id = model.id,
        title = model.title,
        imageUrl = model.imageUrl,
        type = model.type,
        publisher = model.publisher?.let { publisherEntityMapper.mapToData(it) }
    )

    override fun mapToDomain(entity: PlaylistEntity): Playlist = Playlist(
        id = entity.id,
        title = entity.title,
        imageUrl = entity.imageUrl,
        type = entity.type,
        publisher = entity.publisher?.let { publisherEntityMapper.mapToDomain(it) }
    )
}
