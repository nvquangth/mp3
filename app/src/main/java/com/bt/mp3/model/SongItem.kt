package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Song
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class SongItem(
    val id: String? = null,
    val title: String? = null,
    val artists: List<ArtistItem>? = null,
    val artistStr: String? = null,
    val publisher: PublisherItem? = null,
    val streamUrl: String? = null,
    val imageUrl: String? = null
) : ModelItem(), Parcelable

class SongItemMapper @Inject constructor(
    private val artistItemMapper: ArtistItemMapper,
    private val publisherItemMapper: PublisherItemMapper
) : ItemMapper<Song, SongItem> {
    override fun mapToPresentation(model: Song): SongItem = SongItem(
        id = model.id,
        title = model.title,
        artists = model.artists?.map { artistItemMapper.mapToPresentation(it) },
        artistStr = getArtistStr(model.artists?.map { artistItemMapper.mapToPresentation(it) }),
        publisher = model.publisher?.let { publisherItemMapper.mapToPresentation(it) },
        streamUrl = model.streamUrl,
        imageUrl = model.imageUrl
    )

    override fun mapToDomain(item: SongItem): Song = Song(
        id = item.id,
        title = item.title,
        artists = item.artists?.map { artistItemMapper.mapToDomain(it) },
        publisher = item.publisher?.let { publisherItemMapper.mapToDomain(it) },
        streamUrl = item.streamUrl,
        imageUrl = item.imageUrl
    )

    private fun getArtistStr(artists: List<ArtistItem>?): String? {
        val result = arrayListOf<String?>()
        artists?.forEach {
            result.add(it.name)
        }

        return result.joinToString(", ")
    }
}
