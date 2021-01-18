package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Composer
import javax.inject.Inject

data class ComposerEntity(
    val id: String? = null,
    val cover: String? = null,
    val link: String? = null,
    val name: String? = null,
    val playlistId: String? = null,
    val spotlight: Boolean? = null,
    val thumbnail: String? = null,
    val totalFollow: Int? = null
) : ModelEntity()

class ComposerEntityMapper @Inject constructor() : EntityMapper<Composer, ComposerEntity> {
    override fun mapToData(model: Composer): ComposerEntity = ComposerEntity(
        id = model.id,
        cover = model.cover,
        link = model.link,
        name = model.link,
        playlistId = model.playlistId,
        spotlight = model.spotlight,
        thumbnail = model.thumbnail,
        totalFollow = model.totalFollow
    )

    override fun mapToDomain(entity: ComposerEntity): Composer = Composer(
        id = entity.id,
        cover = entity.cover,
        link = entity.link,
        name = entity.link,
        playlistId = entity.playlistId,
        spotlight = entity.spotlight,
        thumbnail = entity.thumbnail,
        totalFollow = entity.totalFollow
    )
}
