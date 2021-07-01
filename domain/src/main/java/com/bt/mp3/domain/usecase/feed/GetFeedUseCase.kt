package com.bt.mp3.domain.usecase.feed

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.FeedRepository
import com.bt.mp3.domain.usecase.UseCase
import com.bt.mp3.entity.Feed
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(private val repository: FeedRepository) : UseCase<GetFeedUseCase.Param, Feed>() {

    data class Param(
        val feedId: String
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): Feed = runCatching {
        param?.let {
            repository.getFeed(it.feedId)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
