package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Radio
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class RadioEntity(
    @SerializedName("encodeId")
    val id: String? = null,
    @SerializedName("PR")
    val pr: Boolean? = null,
    val artists: List<ArtistEntity>? = null,
    val artistsNames: String? = null,
    val isAlbum: Boolean? = null,
    val isShuffle: Boolean? = null,
    val isSingle: Boolean? = null,
    @SerializedName("isoffical")
    val isOfficial: Boolean? = null,
    val link: String? = null,
    val playItemMode: Int? = null,
    val subType: Int? = null,
    val textType: String? = null,
    val thumbnail: String? = null,
    val thumbnailM: String? = null,
    val title: String? = null,
    val uid: Int? = null,
    val userName: String? = null
) : ModelEntity()

class RadioEntityMapper @Inject constructor(
    private val artistEntityMapper: ArtistEntityMapper
) : EntityMapper<Radio, RadioEntity> {
    override fun mapToData(model: Radio): RadioEntity = RadioEntity(
        id = model.id,
        pr = model.pr,
        artists = model.artists?.map { artistEntityMapper.mapToData(it) },
        artistsNames = model.artistsNames,
        isAlbum = model.isAlbum,
        isShuffle = model.isShuffle,
        isSingle = model.isSingle,
        isOfficial = model.isOfficial,
        link = model.link,
        playItemMode = model.playItemMode,
        subType = model.subType,
        textType = model.textType,
        thumbnail = model.thumbnail,
        thumbnailM = model.thumbnailM,
        title = model.title,
        uid = model.uid,
        userName = model.userName
    )

    override fun mapToDomain(entity: RadioEntity): Radio = Radio(
        id = entity.id,
        pr = entity.pr,
        artists = entity.artists?.map { artistEntityMapper.mapToDomain(it) },
        artistsNames = entity.artistsNames,
        isAlbum = entity.isAlbum,
        isShuffle = entity.isShuffle,
        isSingle = entity.isSingle,
        isOfficial = entity.isOfficial,
        link = entity.link,
        playItemMode = entity.playItemMode,
        subType = entity.subType,
        textType = entity.textType,
        thumbnail = entity.thumbnail,
        thumbnailM = entity.thumbnailM,
        title = entity.title,
        uid = entity.uid,
        userName = entity.userName
    )
}
