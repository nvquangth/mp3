package com.bt.mp3.ui.discover

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.bt.base.model.Result
import com.bt.base.ui.BaseViewModel
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.domain.usecase.GetHomePageUseCase
import com.bt.mp3.model.HomePageItem
import com.bt.mp3.model.HomePageItemMapper
import com.bt.mp3.model.SectionItem
import kotlinx.coroutines.CoroutineDispatcher

class DiscoverViewModel @ViewModelInject constructor(
    private val getHomePageUseCase: GetHomePageUseCase,
    private val homePageItemMapper: HomePageItemMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    companion object {
        const val PAGE_DEFAULT: Int = 1
    }

    private val _page = MutableLiveData(PAGE_DEFAULT)
    val page: LiveData<Int>
        get() = _page

    private val _sectionBannerLoaded = MutableLiveData<Boolean>(true)
    private val _sectionZmaLoaded = MutableLiveData<Boolean>(true)
    private val _sectionPlaylistLoaded = MutableLiveData<Boolean>(true)
    private val _sectionGenreLoaded = MutableLiveData<Boolean>(true)
    private val _sectionNewReleaseLoaded = MutableLiveData<Boolean>(true)
    private val _sectionVideoLoaded = MutableLiveData<Boolean>(true)
    private val _sectionSongLoaded = MutableLiveData<Boolean>(true)

    val isLoadMoreHomePage = MediatorLiveData<Boolean>().apply {
        addSource(_sectionBannerLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionZmaLoaded.value == true &&
                    _sectionPlaylistLoaded.value == true &&
                    _sectionGenreLoaded.value == true &&
                    _sectionNewReleaseLoaded.value == true &&
                    _sectionVideoLoaded.value == true &&
                    _sectionSongLoaded.value == true
            }
        }
        addSource(_sectionZmaLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionBannerLoaded.value == true &&
                    _sectionPlaylistLoaded.value == true &&
                    _sectionGenreLoaded.value == true &&
                    _sectionNewReleaseLoaded.value == true &&
                    _sectionVideoLoaded.value == true &&
                    _sectionSongLoaded.value == true
            }
        }
        addSource(_sectionPlaylistLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionBannerLoaded.value == true &&
                    _sectionZmaLoaded.value == true &&
                    _sectionGenreLoaded.value == true &&
                    _sectionNewReleaseLoaded.value == true &&
                    _sectionVideoLoaded.value == true &&
                    _sectionSongLoaded.value == true
            }
        }
        addSource(_sectionGenreLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionBannerLoaded.value == true &&
                    _sectionZmaLoaded.value == true &&
                    _sectionPlaylistLoaded.value == true &&
                    _sectionNewReleaseLoaded.value == true &&
                    _sectionVideoLoaded.value == true &&
                    _sectionSongLoaded.value == true
            }
        }
        addSource(_sectionNewReleaseLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionBannerLoaded.value == true &&
                    _sectionZmaLoaded.value == true &&
                    _sectionPlaylistLoaded.value == true &&
                    _sectionGenreLoaded.value == true &&
                    _sectionVideoLoaded.value == true &&
                    _sectionSongLoaded.value == true
            }
        }

        addSource(_sectionVideoLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionBannerLoaded.value == true &&
                    _sectionZmaLoaded.value == true &&
                    _sectionPlaylistLoaded.value == true &&
                    _sectionGenreLoaded.value == true &&
                    _sectionNewReleaseLoaded.value == true &&
                    _sectionSongLoaded.value == true
            }
        }

        addSource(_sectionSongLoaded) {
            if (it) {
                value = (homePage.value as? Result.Success)?.data?.hasMore == true &&
                    _sectionBannerLoaded.value == true &&
                    _sectionZmaLoaded.value == true &&
                    _sectionPlaylistLoaded.value == true &&
                    _sectionGenreLoaded.value == true &&
                    _sectionNewReleaseLoaded.value == true &&
                    _sectionVideoLoaded.value == true
            }
        }
    }

    val homePage: LiveData<Result<HomePageItem>> = _page.switchMap {
        liveData<Result<HomePageItem>>(defaultDispatcher) {
            runCatching {

                _sectionBannerLoaded.postValue(false)
                _sectionZmaLoaded.postValue(false)
                _sectionPlaylistLoaded.postValue(false)
                _sectionGenreLoaded.postValue(false)
                _sectionNewReleaseLoaded.postValue(false)
                _sectionVideoLoaded.postValue(false)
                _sectionSongLoaded.postValue(false)

                setLoadingAsync(true)
                getHomePageUseCase.execute(GetHomePageUseCase.Param(pageNumber = it)).let {
                    homePageItemMapper.mapToPresentation(it)
                }.let {
                    emit(Result.Success(it))
                    if (it.hasMore == false) {
                        setLoadingAsync(false)
                    }
                }
            }.getOrElse {
                setExceptionAsync(it.mapToCleanException())
            }
        }
    }

    val sectionBanner: LiveData<SectionItem> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.findLast {
                    it.sectionType == SectionItem.TYPE_BANNER
                }.let {
                    it?.let {
                        emit(it)
                    }
                    _sectionBannerLoaded.postValue(true)
                }
            }
        }
    }

    private val _sectionZma = MutableLiveData<List<SectionItem>>()
    val sectionZMA: LiveData<List<SectionItem>> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.filter {
                    it.sectionType == SectionItem.TYPE_PROMOTE
                }.let {
                    (_sectionZma.value ?: emptyList())
                        .toMutableList()
                        .apply {
                            it?.let {
                                addAll(it)
                            }
                        }.run {
                            _sectionZma.postValue(this)
                            _sectionZmaLoaded.postValue(true)
                            emit(this.toList())
                        }
                }
            }
        }
    }

    private val _sectionPlaylist = MutableLiveData<List<SectionItem>>()
    val sectionPlaylist: LiveData<List<SectionItem>> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.filter {
                    it.sectionType == SectionItem.TYPE_PLAYLIST
                }.let {
                    (_sectionPlaylist.value ?: emptyList())
                        .toMutableList()
                        .apply {
                            it?.let {
                                addAll(it)
                            }
                        }.run {
                            _sectionPlaylist.postValue(this)
                            _sectionPlaylistLoaded.postValue(true)
                            emit(this.toList())
                        }
                }
            }
        }
    }

    private val _sectionGenre = MutableLiveData<List<SectionItem>>()
    val sectionGenre: LiveData<List<SectionItem>> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.filter {
                    it.sectionType == SectionItem.TYPE_GENRE
                }.let {
                    (_sectionGenre.value ?: emptyList())
                        .toMutableList()
                        .apply {
                            it?.let {
                                addAll(it)
                            }
                        }.run {
                            _sectionGenre.postValue(this)
                            _sectionGenreLoaded.postValue(true)
                            emit(this.toList())
                        }
                }
            }
        }
    }

    private val _sectionNewRelease = MutableLiveData<List<SectionItem>>()
    val sectionNewRelease: LiveData<List<SectionItem>> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.filter {
                    it.sectionType == SectionItem.TYPE_NEW_RELEASE_CHART
                }.let {
                    (_sectionNewRelease.value ?: emptyList())
                        .toMutableList()
                        .apply {
                            it?.let {
                                addAll(it)
                            }
                        }.run {
                            _sectionNewRelease.postValue(this)
                            _sectionNewReleaseLoaded.postValue(true)
                            emit(this.toList())
                        }
                }
            }
        }
    }

    private val _sectionVideo = MutableLiveData<List<SectionItem>>()
    val sectionVideo: LiveData<List<SectionItem>> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.filter {
                    it.sectionType == SectionItem.TYPE_VIDEO
                }.let {
                    (_sectionVideo.value ?: emptyList())
                        .toMutableList()
                        .apply {
                            it?.let {
                                addAll(it)
                            }
                        }.run {
                            _sectionVideo.postValue(this)
                            _sectionVideoLoaded.postValue(true)
                            emit(this.toList())
                        }
                }
            }
        }
    }

    private val _sectionSong = MutableLiveData<List<SectionItem>>()
    val sectionSong: LiveData<List<SectionItem>> = homePage.switchMap {
        liveData(defaultDispatcher) {
            if (it is Result.Success) {
                it.data.sections?.filter {
                    it.sectionType == SectionItem.TYPE_SONG
                }.let {
                    (_sectionSong.value ?: emptyList())
                        .toMutableList()
                        .apply {
                            it?.let {
                                addAll(it)
                            }
                        }.run {
                            _sectionSong.postValue(this)
                            _sectionSongLoaded.postValue(true)
                            emit(this.toList())
                        }
                }
            }
        }
    }

    fun setPage(page: Int) {
        _page.value = page
    }

    fun getCurrentPage(): Int = _page.value ?: 0
}
