package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Album
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class AlbumEntity(
    @SerializedName("encodeId")
    val id: String? = null,
    @SerializedName("PR")
    val pr: Boolean? = null,
    val artists: List<ArtistEntity>? = null,
    val artistsNames: String? = null,
    @SerializedName("isoffical")
    val isOfficial: Boolean? = null,
    val link: String? = null,
    val thumbnail: String? = null,
    val title: String? = null
) : ModelEntity()

class AlbumEntityMapper @Inject constructor(
    private val artistEntityMapper: ArtistEntityMapper
) : EntityMapper<Album, AlbumEntity> {
    override fun mapToData(model: Album): AlbumEntity = AlbumEntity(
        id = model.id,
        pr = model.pr,
        thumbnail = model.thumbnail,
        link = model.link,
        artists = model.artists?.map { artistEntityMapper.mapToData(it) },
        title = model.title,
        artistsNames = model.artistsNames,
        isOfficial = model.isOfficial
    )

    override fun mapToDomain(entity: AlbumEntity): Album = Album(
        id = entity.id,
        pr = entity.pr,
        thumbnail = entity.thumbnail,
        link = entity.link,
        artists = entity.artists?.map { artistEntityMapper.mapToDomain(it) },
        title = entity.title,
        artistsNames = entity.artistsNames,
        isOfficial = entity.isOfficial
    )
}
