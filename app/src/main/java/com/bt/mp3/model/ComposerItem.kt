package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Composer
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class ComposerItem(
    val id: String? = null,
    val cover: String? = null,
    val link: String? = null,
    val name: String? = null,
    val playlistId: String? = null,
    val spotlight: Boolean? = null,
    val thumbnail: String? = null,
    val totalFollow: Int? = null
) : ModelItem(), Parcelable

class ComposerItemMapper @Inject constructor() : ItemMapper<Composer, ComposerItem> {
    override fun mapToPresentation(model: Composer): ComposerItem = ComposerItem(
        id = model.id,
        cover = model.cover,
        link = model.link,
        name = model.link,
        playlistId = model.playlistId,
        spotlight = model.spotlight,
        thumbnail = model.thumbnail,
        totalFollow = model.totalFollow
    )

    override fun mapToDomain(item: ComposerItem): Composer = Composer(
        id = item.id,
        cover = item.cover,
        link = item.link,
        name = item.link,
        playlistId = item.playlistId,
        spotlight = item.spotlight,
        thumbnail = item.thumbnail,
        totalFollow = item.totalFollow
    )
}
