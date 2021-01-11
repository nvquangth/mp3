package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Publisher
import javax.inject.Inject

data class PublisherEntity(
    val id: String? = null,
    val name: String? = null
) : ModelEntity()

class PublisherEntityMapper @Inject constructor() : EntityMapper<Publisher, PublisherEntity> {

    override fun mapToDomain(entity: PublisherEntity): Publisher = Publisher(
        id = entity.id,
        name = entity.name
    )

    override fun mapToData(model: Publisher): PublisherEntity = PublisherEntity(
        id = model.id,
        name = model.name
    )
}
