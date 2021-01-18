package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Genre
import javax.inject.Inject

data class GenreEntity(
    val id: String? = null,
    val alias: String? = null,
    val link: String? = null,
    val name: String? = null,
    val title: String? = null
) : ModelEntity()

class GenreEntityMapper @Inject constructor() : EntityMapper<Genre, GenreEntity> {
    override fun mapToData(model: Genre): GenreEntity = GenreEntity(
        id = model.id,
        alias = model.alias,
        link = model.link,
        name = model.name,
        title = model.title
    )

    override fun mapToDomain(entity: GenreEntity): Genre = Genre(
        id = entity.id,
        alias = entity.alias,
        link = entity.link,
        name = entity.name,
        title = entity.title
    )
}
