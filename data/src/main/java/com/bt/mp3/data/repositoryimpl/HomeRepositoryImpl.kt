package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.data.model.HomePageEntityMapper
import com.bt.mp3.data.network.HomeRemoteDataSource
import com.bt.mp3.data.network.retrofit.HomeRetrofitApi
import com.bt.mp3.domain.repository.HomeRepository
import com.bt.mp3.entity.HomePage
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val homePageEntityMapper: HomePageEntityMapper
) : HomeRepository {

    override suspend fun getHomePage(pageNumber: Int): HomePage = runCatching {
        remote.getHomePage(pageNumber).data?.let { homePageEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
