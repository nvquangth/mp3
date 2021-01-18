package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Album
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class AlbumItem(
    val id: String? = null,
    val pr: Boolean? = null,
    val artists: List<ArtistItem>? = null,
    val artistsNames: String? = null,
    val isOfficial: Boolean? = null,
    val link: String? = null,
    val thumbnail: String? = null,
    val title: String? = null
) : ModelItem(), Parcelable

class AlbumItemMapper @Inject constructor(
    private val artistItemMapper: ArtistItemMapper
) : ItemMapper<Album, AlbumItem> {

    override fun mapToPresentation(model: Album): AlbumItem = AlbumItem(
        id = model.id,
        pr = model.pr,
        thumbnail = model.thumbnail,
        link = model.link,
        artists = model.artists?.map { artistItemMapper.mapToPresentation(it) },
        title = model.title,
        artistsNames = model.artistsNames,
        isOfficial = model.isOfficial
    )

    override fun mapToDomain(item: AlbumItem): Album = Album(
        id = item.id,
        pr = item.pr,
        thumbnail = item.thumbnail,
        link = item.link,
        artists = item.artists?.map { artistItemMapper.mapToDomain(it) },
        title = item.title,
        artistsNames = item.artistsNames,
        isOfficial = item.isOfficial
    )
}
