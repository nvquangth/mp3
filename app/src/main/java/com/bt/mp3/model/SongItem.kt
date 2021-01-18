package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Song
import dagger.Lazy
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class SongItem(
    val id: String? = null,
    val album: AlbumItem? = null,
    val alias: String? = null,
    val allowAudioAds: Boolean? = null,
    val artists: List<ArtistItem>? = null,
    val artistsNames: String? = null,
    val comment: Int? = null,
    val composers: List<ComposerItem>? = null,
    val duration: Int? = null,
    val genres: List<GenreItem>? = null,
    val hasLyric: Boolean? = null,
    val isOfficial: Boolean? = null,
    val isPrivate: Boolean? = null,
    val isRBT: Boolean? = null,
    val isWorldWide: Boolean? = null,
    val isZMA: Boolean? = null,
    val like: Int? = null,
    val liked: Boolean? = null,
    val link: String? = null,
    val listen: Int? = null,
    val mvLink: String? = null,
    val preRelease: Boolean? = null,
    val radio: RadioItem? = null,
    val releaseDate: Int? = null,
    val sections: List<SectionItem>? = null,
    val streamingStatus: Int? = null,
    val thumbnail: String? = null,
    val thumbnailM: String? = null,
    val title: String? = null,
    val userId: Int? = null,
    val username: String? = null,
    val zingChoice: Boolean? = null,
    val banner: String? = null,
    val target: String? = null,
    val cover: String? = null,
    val description: String? = null,
    val songs: List<PlaylistInfoItem>? = null
) : ModelItem(), Parcelable {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

open class SongItemMapper @Inject constructor(
    private val albumItemMapper: AlbumItemMapper,
    private val artistItemMapper: ArtistItemMapper,
    private val composerItemMapper: ComposerItemMapper,
    private val genreItemMapper: GenreItemMapper,
    private val radioItemMapper: RadioItemMapper,
    private val sectionItemMapper: Lazy<SectionItemMapper>,
    private val playlistInfoItemMapper: Lazy<PlaylistInfoItemMapper>
) : ItemMapper<Song, SongItem> {

    override fun mapToPresentation(model: Song): SongItem = SongItem(
        id = model.id,
        album = model.album?.let { albumItemMapper.mapToPresentation(it) },
        alias = model.alias,
        allowAudioAds = model.allowAudioAds,
        artists = model.artists?.map { artistItemMapper.mapToPresentation(it) },
        artistsNames = model.artistsNames,
        comment = model.comment,
        composers = model.composers?.map { composerItemMapper.mapToPresentation(it) },
        duration = model.duration,
        genres = model.genres?.map { genreItemMapper.mapToPresentation(it) },
        hasLyric = model.hasLyric,
        isOfficial = model.isOfficial,
        isPrivate = model.isPrivate,
        isRBT = model.isRBT,
        isWorldWide = model.isWorldWide,
        isZMA = model.isZMA,
        like = model.like,
        liked = model.liked,
        link = model.link,
        listen = model.listen,
        mvLink = model.mvLink,
        preRelease = model.preRelease,
        radio = model.radio?.let { radioItemMapper.mapToPresentation(it) },
        releaseDate = model.releaseDate,
        sections = model.sections?.map { sectionItemMapper.get().mapToPresentation(it) },
        streamingStatus = model.streamingStatus,
        thumbnail = model.thumbnail,
        thumbnailM = model.thumbnailM,
        title = model.title,
        userId = model.userId,
        username = model.username,
        zingChoice = model.zingChoice,
        banner = model.banner,
        target = model.target,
        cover = model.cover,
        description = model.description,
        songs = model.songs?.map { playlistInfoItemMapper.get().mapToPresentation(it) }
    )

    override fun mapToDomain(item: SongItem): Song = Song(
        id = item.id,
        album = item.album?.let { albumItemMapper.mapToDomain(it) },
        alias = item.alias,
        allowAudioAds = item.allowAudioAds,
        artists = item.artists?.map { artistItemMapper.mapToDomain(it) },
        artistsNames = item.artistsNames,
        comment = item.comment,
        composers = item.composers?.map { composerItemMapper.mapToDomain(it) },
        duration = item.duration,
        genres = item.genres?.map { genreItemMapper.mapToDomain(it) },
        hasLyric = item.hasLyric,
        isOfficial = item.isOfficial,
        isPrivate = item.isPrivate,
        isRBT = item.isRBT,
        isWorldWide = item.isWorldWide,
        isZMA = item.isZMA,
        like = item.like,
        liked = item.liked,
        link = item.link,
        listen = item.listen,
        mvLink = item.mvLink,
        preRelease = item.preRelease,
        radio = item.radio?.let { radioItemMapper.mapToDomain(it) },
        releaseDate = item.releaseDate,
        sections = item.sections?.map { sectionItemMapper.get().mapToDomain(it) },
        streamingStatus = item.streamingStatus,
        thumbnail = item.thumbnail,
        thumbnailM = item.thumbnailM,
        title = item.title,
        userId = item.userId,
        username = item.username,
        zingChoice = item.zingChoice,
        banner = item.banner,
        target = item.target,
        cover = item.cover,
        description = item.description,
        songs = item.songs?.map { playlistInfoItemMapper.get().mapToDomain(it) }
    )
}
