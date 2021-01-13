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
    val name: String? = null
) : ModelItem(), Parcelable

class ArtistItemMapper @Inject constructor() : ItemMapper<Artist, ArtistItem> {
    override fun mapToPresentation(model: Artist): ArtistItem = ArtistItem(
        id = model.id,
        name = model.name
    )

    override fun mapToDomain(item: ArtistItem): Artist = Artist(
        id = item.id,
        name = item.name
    )
}
