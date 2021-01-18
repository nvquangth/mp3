package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Artist
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class ArtistItem(
    val id: String? = null,
    val name: String? = null,
    val link: String? = null,
    val spotlight: Boolean? = null,
    val cover: String? = null,
    val thumbnail: String? = null,
    val playlistId: String? = null,
    val totalFollow: String? = null
) : ModelItem(), Parcelable

class ArtistItemMapper @Inject constructor() : ItemMapper<Artist, ArtistItem> {
    override fun mapToPresentation(model: Artist): ArtistItem = ArtistItem(
        id = model.id,
        name = model.name,
        link = model.link,
        spotlight = model.spotlight,
        cover = model.cover,
        thumbnail = model.thumbnail,
        playlistId = model.playlistId,
        totalFollow = model.totalFollow
    )

    override fun mapToDomain(item: ArtistItem): Artist = Artist(
        id = item.id,
        name = item.name,
        link = item.link,
        spotlight = item.spotlight,
        cover = item.cover,
        thumbnail = item.thumbnail,
        playlistId = item.playlistId,
        totalFollow = item.totalFollow
    )
}
