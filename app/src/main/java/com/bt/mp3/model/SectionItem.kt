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
) : ModelItem(), Parcelable {

    companion object {
        const val TYPE_PLAYLIST = "playlist"
        const val TYPE_PROMOTE = "promoteSlider"
        const val TYPE_ADS_BANNER = "adBanner"
        const val TYPE_BANNER = "banner"
        const val TYPE_GENRE = "genre_mood"
        const val TYPE_REALTIME_CHART = "RTChart"
        const val TYPE_WEEK_CHART = "weekChart"
        const val TYPE_NEW_RELEASE_CHART = "newReleaseChart"
        const val TYPE_ARTIST_SPOTLIGHT = "artistSpotlight"
        const val TYPE_SONG = "song"
        const val TYPE_VIDEO = "video"

        const val ID_RADIO = "hRadio"
        const val ID_AUTO_THEME_1 = "hAutoTheme1"
        const val ID_AUTO_THEME_2 = "hAutoTheme2"
        const val ID_ALBUM = "hAlbum"
        const val ID_TOP_100 = "h100"
        const val ID_UNKNOWN = ""
        const val ID_GENRE = "hGenre"
        const val ID_REALTIME_CHART = "hZC"
        const val ID_NEW_RELEASE_CHART = "hNewrelease"
        const val ID_NEW_SONG = "hNewSong"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

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
