package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Artist
import javax.inject.Inject

data class ArtistEntity(
    val id: String? = null,
    val name: String? = null
) : ModelEntity()

class ArtistEntityMapper @Inject constructor() : EntityMapper<Artist, ArtistEntity> {
    override fun mapToData(model: Artist): ArtistEntity = ArtistEntity(
        id = model.id,
        name = model.name
    )

    override fun mapToDomain(entity: ArtistEntity): Artist = Artist(
        id = entity.id,
        name = entity.name
    )
}
