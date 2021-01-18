package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.PlaylistInfo
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class PlaylistInfoItem(
    val items: List<SongItem>? = null,
    val total: Int? = null,
    val totalDuration: Long? = null
) : ModelItem(), Parcelable

class PlaylistInfoItemMapper @Inject constructor(
    private val songItemMapper: SongItemMapper
) : ItemMapper<PlaylistInfo, PlaylistInfoItem> {
    override fun mapToPresentation(model: PlaylistInfo): PlaylistInfoItem = PlaylistInfoItem(
        items = model.items?.map { songItemMapper.mapToPresentation(it) },
        total = model.total,
        totalDuration = model.totalDuration
    )

    override fun mapToDomain(item: PlaylistInfoItem): PlaylistInfo = PlaylistInfo(
        items = item.items?.map { songItemMapper.mapToDomain(it) },
        total = item.total,
        totalDuration = item.totalDuration
    )
}
