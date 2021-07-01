package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.MediaSource
import javax.inject.Inject

data class MediaSourceEntity(
    val s360: String? = null,
    val s480: String? = null,
    val s720: String? = null,
    val ratio: Float? = null,
    val thumbnail: String? = null,
) : ModelEntity()

class MediaSourceEntityMapper @Inject constructor() : EntityMapper<MediaSource, MediaSourceEntity> {

    override fun mapToData(model: MediaSource): MediaSourceEntity = MediaSourceEntity(
        s360 = model.s360,
        s480 = model.s480,
        s720 = model.s720,
        ratio = model.ratio,
        thumbnail = model.thumbnail
    )

    override fun mapToDomain(entity: MediaSourceEntity): MediaSource = MediaSource(
        s360 = entity.s360,
        s480 = entity.s480,
        s720 = entity.s720,
        ratio = entity.ratio,
        thumbnail = entity.thumbnail
    )
}
