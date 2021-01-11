package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Playlist
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class PlaylistItem(
    val id: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val type: PlaylistTypeItem? = null,
    val publisher: PublisherItem? = null
) : ModelItem(), Parcelable

class PlaylistItemMapper @Inject constructor(
    private val publisherItemMapper: PublisherItemMapper
) : ItemMapper<Playlist, PlaylistItem> {

    override fun mapToPresentation(model: Playlist): PlaylistItem = PlaylistItem(
        id = model.id,
        title = model.title,
        imageUrl = model.imageUrl,
        type = PlaylistTypeItemMapper.mapToPresentation(model.type),
        publisher = model.publisher?.let { publisherItemMapper.mapToPresentation(it) }
    )

    override fun mapToDomain(item: PlaylistItem): Playlist = Playlist(
        id = item.id,
        title = item.title,
        imageUrl = item.imageUrl,
        type = PlaylistTypeItemMapper.mapToDomain(item.type),
        publisher = item.publisher?.let { publisherItemMapper.mapToDomain(it) }
    )
}
