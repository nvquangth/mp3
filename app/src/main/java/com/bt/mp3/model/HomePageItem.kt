package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.HomePage
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class HomePageItem(
    val sections: List<SectionItem>? = null,
    val hasMore: Boolean? = null,
    val total: Int? = null
) : ModelItem(), Parcelable

class HomePageItemMapper @Inject constructor(
    private val sectionItemMapper: SectionItemMapper
) : ItemMapper<HomePage, HomePageItem> {
    override fun mapToPresentation(model: HomePage): HomePageItem = HomePageItem(
        sections = model.sections?.map { sectionItemMapper.mapToPresentation(it) },
        hasMore = model.hasMore,
        total = model.total
    )

    override fun mapToDomain(item: HomePageItem): HomePage = HomePage(
        sections = item.sections?.map { sectionItemMapper.mapToDomain(it) },
        hasMore = item.hasMore,
        total = item.total
    )
}
