package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.ContentFeed
import javax.inject.Inject

data class ContentFeedEntity(
    val type: String? = null,
    val layoutType: Int? = null,
    val photoSize: Int? = null,
    val photos: List<PhotoEntity>? = null,
    val thumbnail: String? = null,
    val mediaSource: MediaSourceEntity? = null
) : ModelEntity()

class ContentFeedEntityMapper @Inject constructor(
    private val photoEntityMapper: PhotoEntityMapper,
    private val mediaSourceEntityMapper: MediaSourceEntityMapper
) : EntityMapper<ContentFeed, ContentFeedEntity> {

    override fun mapToData(model: ContentFeed): ContentFeedEntity = ContentFeedEntity(
        type = model.type,
        layoutType = model.layoutType,
        photoSize = model.photoSize,
        photos = model.photos?.map { photoEntityMapper.mapToData(it) },
        thumbnail = model.thumbnail,
        mediaSource = model.mediaSource?.let { mediaSourceEntityMapper.mapToData(it) }
    )

    override fun mapToDomain(entity: ContentFeedEntity): ContentFeed = ContentFeed(
        type = entity.type,
        layoutType = entity.layoutType,
        photoSize = entity.photoSize,
        photos = entity.photos?.map { photoEntityMapper.mapToDomain(it) },
        thumbnail = entity.thumbnail,
        mediaSource = entity.mediaSource?.let { mediaSourceEntityMapper.mapToDomain(it) }
    )
}
