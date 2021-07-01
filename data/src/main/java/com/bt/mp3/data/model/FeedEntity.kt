package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Feed
import javax.inject.Inject

data class FeedEntity(
    val id: String,
    val content: ContentFeedEntity? = null,
    val type: Int? = null,
    val title: String? = null,
    val createdTime: Long? = null,
    val publishTime: Long? = null,
    val shortDescription: String? = null,
    val description: String? = null,
    val sourceType: Int? = null,
    val publisher: ArtistEntity? = null,
    val status: String? = null,
    val like: Long? = null,
    val comment: Long? = null,
    val liked: Boolean? = null
) : ModelEntity()

class FeedEntityMapper @Inject constructor(
    private val contentFeedEntityMapper: ContentFeedEntityMapper,
    private val artistEntityMapper: ArtistEntityMapper
) : EntityMapper<Feed, FeedEntity> {

    override fun mapToData(model: Feed): FeedEntity = FeedEntity(
        id = model.id,
        content = model.content?.let { contentFeedEntityMapper.mapToData(it) },
        type = model.type,
        title = model.title,
        createdTime = model.createdTime,
        publishTime = model.publishTime,
        shortDescription = model.shortDescription,
        description = model.description,
        sourceType = model.sourceType,
        publisher = model.publisher?.let { artistEntityMapper.mapToData(it) },
        status = model.status,
        like = model.like,
        comment = model.comment,
        liked = model.liked
    )

    override fun mapToDomain(entity: FeedEntity): Feed = Feed(
        id = entity.id,
        content = entity.content?.let { contentFeedEntityMapper.mapToDomain(it) },
        type = entity.type,
        title = entity.title,
        createdTime = entity.createdTime,
        publishTime = entity.publishTime,
        shortDescription = entity.shortDescription,
        description = entity.description,
        sourceType = entity.sourceType,
        publisher = entity.publisher?.let { artistEntityMapper.mapToDomain(it) },
        status = entity.status,
        like = entity.like,
        comment = entity.comment,
        liked = entity.liked
    )
}
