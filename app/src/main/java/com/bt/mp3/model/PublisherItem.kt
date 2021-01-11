package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Publisher
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class PublisherItem(
    val id: String? = null,
    val name: String? = null
) : ModelItem(), Parcelable

class PublisherItemMapper @Inject constructor() : ItemMapper<Publisher, PublisherItem> {

    override fun mapToPresentation(model: Publisher): PublisherItem = PublisherItem(
        id = model.id,
        name = model.name
    )

    override fun mapToDomain(item: PublisherItem): Publisher = Publisher(
        id = item.id,
        name = item.name
    )
}
