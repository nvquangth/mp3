package com.bt.mp3.domain.usecase.feed

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.FeedRepository
import com.bt.mp3.domain.usecase.UseCase
import com.bt.mp3.entity.Feed
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetFeedsUseCase @Inject constructor(private val repository: FeedRepository) : UseCase<GetFeedsUseCase.Param, List<Feed>>() {

    data class Param(val page: Int = 1) : UseCase.Param()

    override suspend fun execute(param: Param?): List<Feed> = runCatching {
        param?.let {
            repository.getFeeds(page = it.page)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
