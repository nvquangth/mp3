package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Song
import com.google.gson.annotations.SerializedName
import dagger.Lazy
import javax.inject.Inject

data class SongEntity(
    @SerializedName("encodeId")
    val id: String? = null,
    val album: AlbumEntity? = null,
    val alias: String? = null,
    val allowAudioAds: Boolean? = null,
    val artists: List<ArtistEntity>? = null,
    val artist: ArtistEntity? = null,
    val artistsNames: String? = null,
    val comment: Int? = null,
    val composers: List<ComposerEntity>? = null,
    val duration: Int? = null,
    val genres: List<GenreEntity>? = null,
    val hasLyric: Boolean? = null,
    @SerializedName("isOffical")
    val isOfficial: Boolean? = null,
    val isPrivate: Boolean? = null,
    val isRBT: Boolean? = null,
    val isWorldWide: Boolean? = null,
    val isZMA: Boolean? = null,
    val like: Int? = null,
    val liked: Boolean? = null,
    val link: String? = null,
    val listen: Int? = null,
    @SerializedName("mvlink")
    val mvLink: String? = null,
    val preRelease: Boolean? = null,
    val radio: RadioEntity? = null,
    val releaseDate: Int? = null,
    val sections: List<SectionEntity>? = null,
    val streamingStatus: Int? = null,
    val thumbnail: String? = null,
    val thumbnailM: String? = null,
    val thumbnailR: String? = null,
    val thumbnailHasText: String? = null,
    val title: String? = null,
    @SerializedName("userid")
    val userId: Int? = null,
    @SerializedName("userName")
    val username: String? = null,
    @SerializedName("zingChoise")
    val zingChoice: Boolean? = null,
    val banner: String? = null,
    val target: String? = null,
    val cover: String? = null,
    val description: String? = null,
    val songs: List<PlaylistInfoEntity>? = null
) : ModelEntity()

class SongEntityMapper @Inject constructor(
    private val albumEntityMapper: AlbumEntityMapper,
    private val artistEntityMapper: ArtistEntityMapper,
    private val composerEntityMapper: ComposerEntityMapper,
    private val genreEntityMapper: GenreEntityMapper,
    private val radioEntityMapper: RadioEntityMapper,
    private val sectionEntityMapper: Lazy<SectionEntityMapper>,
    private val playlistInfoEntityMapper: Lazy<PlaylistInfoEntityMapper>
) : EntityMapper<Song, SongEntity> {
    override fun mapToData(model: Song): SongEntity = SongEntity(
        id = model.id,
        album = model.album?.let { albumEntityMapper.mapToData(it) },
        alias = model.alias,
        allowAudioAds = model.allowAudioAds,
        artists = model.artists?.map { artistEntityMapper.mapToData(it) },
        artist = model.artist?.let { artistEntityMapper.mapToData(it) },
        artistsNames = model.artistsNames,
        comment = model.comment,
        composers = model.composers?.map { composerEntityMapper.mapToData(it) },
        duration = model.duration,
        genres = model.genres?.map { genreEntityMapper.mapToData(it) },
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
        radio = model.radio?.let { radioEntityMapper.mapToData(it) },
        releaseDate = model.releaseDate,
        sections = model.sections?.map { sectionEntityMapper.get().mapToData(it) },
        streamingStatus = model.streamingStatus,
        thumbnail = model.thumbnail,
        thumbnailM = model.thumbnailM,
        thumbnailR = model.thumbnailR,
        thumbnailHasText = model.thumbnailHasText,
        title = model.title,
        userId = model.userId,
        username = model.username,
        zingChoice = model.zingChoice,
        banner = model.banner,
        target = model.target,
        cover = model.cover,
        description = model.description,
        songs = model.songs?.map { playlistInfoEntityMapper.get().mapToData(it) }
    )

    override fun mapToDomain(entity: SongEntity): Song = Song(
        id = entity.id,
        album = entity.album?.let { albumEntityMapper.mapToDomain(it) },
        alias = entity.alias,
        allowAudioAds = entity.allowAudioAds,
        artists = entity.artists?.map { artistEntityMapper.mapToDomain(it) },
        artist = entity.artist?.let { artistEntityMapper.mapToDomain(it) },
        artistsNames = entity.artistsNames,
        comment = entity.comment,
        composers = entity.composers?.map { composerEntityMapper.mapToDomain(it) },
        duration = entity.duration,
        genres = entity.genres?.map { genreEntityMapper.mapToDomain(it) },
        hasLyric = entity.hasLyric,
        isOfficial = entity.isOfficial,
        isPrivate = entity.isPrivate,
        isRBT = entity.isRBT,
        isWorldWide = entity.isWorldWide,
        isZMA = entity.isZMA,
        like = entity.like,
        liked = entity.liked,
        link = entity.link,
        listen = entity.listen,
        mvLink = entity.mvLink,
        preRelease = entity.preRelease,
        radio = entity.radio?.let { radioEntityMapper.mapToDomain(it) },
        releaseDate = entity.releaseDate,
        sections = entity.sections?.map { sectionEntityMapper.get().mapToDomain(it) },
        streamingStatus = entity.streamingStatus,
        thumbnail = entity.thumbnail,
        thumbnailM = entity.thumbnailM,
        thumbnailR = entity.thumbnailR,
        thumbnailHasText = entity.thumbnailHasText,
        title = entity.title,
        userId = entity.userId,
        username = entity.username,
        zingChoice = entity.zingChoice,
        banner = entity.banner,
        target = entity.target,
        cover = entity.cover,
        description = entity.description,
        songs = entity.songs?.map { playlistInfoEntityMapper.get().mapToDomain(it) }
    )
}
