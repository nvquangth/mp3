package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Radio
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class RadioItem(
    val id: String? = null,
    val pr: Boolean? = null,
    val artists: List<ArtistItem>? = null,
    val artistsNames: String? = null,
    val isAlbum: Boolean? = null,
    val isShuffle: Boolean? = null,
    val isSingle: Boolean? = null,
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
) : ModelItem(), Parcelable

class RadioItemMapper @Inject constructor(
    private val artistItemMapper: ArtistItemMapper
) : ItemMapper<Radio, RadioItem> {
    override fun mapToPresentation(model: Radio): RadioItem = RadioItem(
        id = model.id,
        pr = model.pr,
        artists = model.artists?.map { artistItemMapper.mapToPresentation(it) },
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

    override fun mapToDomain(item: RadioItem): Radio = Radio(
        id = item.id,
        pr = item.pr,
        artists = item.artists?.map { artistItemMapper.mapToDomain(it) },
        artistsNames = item.artistsNames,
        isAlbum = item.isAlbum,
        isShuffle = item.isShuffle,
        isSingle = item.isSingle,
        isOfficial = item.isOfficial,
        link = item.link,
        playItemMode = item.playItemMode,
        subType = item.subType,
        textType = item.textType,
        thumbnail = item.thumbnail,
        thumbnailM = item.thumbnailM,
        title = item.title,
        uid = item.uid,
        userName = item.userName
    )
}
