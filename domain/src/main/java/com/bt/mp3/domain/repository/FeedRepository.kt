package com.bt.mp3.domain.repository

import com.bt.mp3.entity.Feed

interface FeedRepository {

    suspend fun getFeed(id: String): Feed

    suspend fun getFeeds(page: Int): List<Feed>
}
