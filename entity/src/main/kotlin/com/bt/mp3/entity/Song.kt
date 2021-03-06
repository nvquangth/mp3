package com.bt.mp3.entity

data class Song(
    val id: String? = null,
    val album: Album? = null,
    val alias: String? = null,
    val allowAudioAds: Boolean? = null,
    val artists: List<Artist>? = null,
    val artist: Artist? = null,
    val artistsNames: String? = null,
    val comment: Int? = null,
    val composers: List<Composer>? = null,
    val duration: Int? = null,
    val genres: List<Genre>? = null,
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
    val radio: Radio? = null,
    val releaseDate: Int? = null,
    val sections: List<Section>? = null,
    val streamingStatus: Int? = null,
    val thumbnail: String? = null,
    val thumbnailM: String? = null,
    val thumbnailR: String? = null,
    val thumbnailHasText: String? = null,
    val title: String? = null,
    val userId: Int? = null,
    val username: String? = null,
    val zingChoice: Boolean? = null,
    val banner: String? = null,
    val target: String? = null,
    val cover: String? = null,
    val description: String? = null,
    val songs: List<PlaylistInfo>? = null
) : Model()
