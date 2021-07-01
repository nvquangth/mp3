package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Photo
import javax.inject.Inject

data class PhotoEntity(
    val id: String,
    val url: String? = null
) : ModelEntity()

class PhotoEntityMapper @Inject constructor() : EntityMapper<Photo, PhotoEntity> {

    override fun mapToData(model: Photo): PhotoEntity = PhotoEntity(
        id = model.id,
        url = model.url
    )

    override fun mapToDomain(entity: PhotoEntity): Photo = Photo(
        id = entity.id,
        url = entity.url
    )
}
