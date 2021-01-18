package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Section
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class SectionItem(
    val items: List<SongItem>? = null,
    val link: String? = null,
    val sectionId: String? = null,
    val sectionType: String? = null,
    val title: String? = null,
    val viewType: String? = null
) : ModelItem(), Parcelable

class SectionItemMapper @Inject constructor(
    private val songItemMapper: SongItemMapper
) : ItemMapper<Section, SectionItem> {
    override fun mapToPresentation(model: Section): SectionItem = SectionItem(
        items = model.items?.map { songItemMapper.mapToPresentation(it) },
        link = model.link,
        sectionId = model.sectionId,
        sectionType = model.sectionType,
        title = model.title,
        viewType = model.viewType
    )

    override fun mapToDomain(item: SectionItem): Section = Section(
        items = item.items?.map { songItemMapper.mapToDomain(it) },
        link = item.link,
        sectionId = item.sectionId,
        sectionType = item.sectionType,
        title = item.title,
        viewType = item.viewType
    )
}
