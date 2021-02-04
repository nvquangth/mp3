package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.data.model.SongEntityMapper
import com.bt.mp3.data.model.StreamEntityMapper
import com.bt.mp3.data.network.SongApi
import com.bt.mp3.domain.repository.SongRepository
import com.bt.mp3.entity.Song
import com.bt.mp3.entity.Stream
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject


class SongRepositoryImpl @Inject constructor(
    private val api: SongApi,
    private val songEntityMapper: SongEntityMapper,
    private val streamEntityMapper: StreamEntityMapper
) : SongRepository {
    override suspend fun getRecentSongs(): List<Song> = runCatching {
        api.getRecentSong().map { songEntityMapper.mapToDomain(it) }
    }.getOrElse {
        throw it.mapToCleanException()
    }

    override suspend fun getDetailSong(songId: String): Song = runCatching {
        api.getDetailSong(songId).data?.let { songEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }

    override suspend fun getStreamSong(songId: String): Stream = runCatching {
        api.getStreamSong(songId).data?.let { streamEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }

    override suspend fun getDetailSong2(songId: String) = runCatching {
        val hashMap = HashMap<String, String>()
        hashMap["id"] = "ZW9DFW8O"
        val hashMap2 = HashMap<String, String>()
        C4(hashMap, hashMap2)
        api.getDetailSong2(hashMap2)
        val x = 2
    }.getOrElse {
        throw it.mapToCleanException()
    }

    fun A4(str: String, hashMap: HashMap<String, String>) {
        hashMap["cTime"] = str
        hashMap["appVersion"] = "21.01.01"
        hashMap["deviceId"] = "1i4ir89t9yiuugjgir884"
        hashMap["os"] = "Android"
        hashMap["osVersion"] = "10"
    }

    fun D4(hashMap: HashMap<String, String>, hashMap2: HashMap<String, String>, hashMap3: HashMap<String, String>) {
//        val valueOf = System.currentTimeMillis().toString()
//        A4(valueOf, hashMap2)
//        for ((key, value) in hashMap.entries) {
//            hashMap2[key] = value
//        }
//        hashMap3["sig"] = Crypto.b(hashMap2)
//        hashMap3["cTime"] = valueOf
//        for ((key, value) in hashMap.entries) {
//            hashMap3[key] = value
//        }
    }

    fun C4(hashMap: HashMap<String, String>, hashMap2: HashMap<String, String>) {
        D4(hashMap, HashMap(), hashMap2)
    }
}
