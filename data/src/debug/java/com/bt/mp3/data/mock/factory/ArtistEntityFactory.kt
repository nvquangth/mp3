package com.bt.mp3.data.mock.factory

import com.bt.mp3.data.model.ArtistEntity
import java.util.UUID

object ArtistEntityFactory {

    fun createArtistEntity(
        name: String? = null
    ): ArtistEntity = ArtistEntity(
        id = UUID.randomUUID().toString(),
        name = name?.let { it } ?: "Lương Bích Hữu"
    )

    fun createArtistEntities(vararg name: String?): List<ArtistEntity> =
        if (name.isNullOrEmpty()) {
            listOf(
                createArtistEntity("Lương Bích Hữu"),
                createArtistEntity("Lam Trường"),
            )
        } else {
            val result = arrayListOf<ArtistEntity>()
            name.forEach {
                result.add(createArtistEntity(it))
            }

            result
        }
}
