package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Song
import javax.inject.Inject

data class SongEntity(
    val id: String? = null,
    val title: String? = null,
    val artists: List<ArtistEntity>? = null,
    val publisher: PublisherEntity? = null,
    val streamUrl: String? = null,
    val imageUrl: String? = null
) : ModelEntity()

class SongEntityMapper @Inject constructor(
    private val artistEntityMapper: ArtistEntityMapper,
    private val publisherEntityMapper: PublisherEntityMapper
) : EntityMapper<Song, SongEntity> {
    override fun mapToData(model: Song): SongEntity = SongEntity(
        id = model.id,
        title = model.title,
        artists = model.artists?.map { artistEntityMapper.mapToData(it) },
        publisher = model.publisher?.let { publisherEntityMapper.mapToData(it) },
        streamUrl = model.streamUrl,
        imageUrl = model.imageUrl
    )

    override fun mapToDomain(entity: SongEntity): Song = Song(
        id = entity.id,
        title = entity.title,
        artists = entity.artists?.map { artistEntityMapper.mapToDomain(it) },
        publisher = entity.publisher?.let { publisherEntityMapper.mapToDomain(it) },
        streamUrl = entity.streamUrl,
        imageUrl = entity.imageUrl
    )
}
