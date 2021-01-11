package com.bt.mp3.data.mock.factory

import com.bt.mp3.data.model.PublisherEntity
import java.util.UUID

object PublisherEntityFactory {

    fun createPublisherEntity(name: String? = null): PublisherEntity = PublisherEntity(
        id = UUID.randomUUID().toString(),
        name = name?.let { it } ?: "Zing MP3"
    )
}
