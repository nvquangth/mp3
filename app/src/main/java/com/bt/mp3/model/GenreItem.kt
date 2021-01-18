package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Genre
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class GenreItem(
    val id: String? = null,
    val alias: String? = null,
    val link: String? = null,
    val name: String? = null,
    val title: String? = null
) : ModelItem(), Parcelable

class GenreItemMapper @Inject constructor() : ItemMapper<Genre, GenreItem> {
    override fun mapToPresentation(model: Genre): GenreItem = GenreItem(
        id = model.id,
        alias = model.alias,
        link = model.link,
        name = model.name,
        title = model.title
    )

    override fun mapToDomain(item: GenreItem): Genre = Genre(
        id = item.id,
        alias = item.alias,
        link = item.link,
        name = item.name,
        title = item.title
    )
}
