package com.bt.mp3.ui.chart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.bt.base.ui.BaseViewModel
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.domain.usecase.GetSectionUseCase
import com.bt.mp3.model.SectionItem
import com.bt.mp3.model.SectionItemMapper
import com.bt.mp3.model.SongItem
import kotlinx.coroutines.CoroutineDispatcher

class ChartViewModel @ViewModelInject constructor(
    private val getSectionUseCase: GetSectionUseCase,
    private val sectionItemMapper: SectionItemMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    companion object {

        // so luong moc thoi gian hien thi tren do thi
        const val k = 12
    }

    private val _sectionChart: LiveData<SectionItem> = liveData(defaultDispatcher) {
        setLoadingAsync(true)

        runCatching {
            getSectionUseCase.execute(GetSectionUseCase.Param("RTChart")).let {
                emit(sectionItemMapper.mapToPresentation(it))
            }
        }.getOrElse {
            it.printStackTrace()
            setExceptionAsync(it.mapToCleanException())
        }
    }

    val dataChart: LiveData<ArrayList<ArrayList<Pair<Long, Long>>>> = _sectionChart.switchMap {
        liveData(defaultDispatcher) {
            runCatching {
                val result = arrayListOf<ArrayList<Pair<Long, Long>>>()
                it.chart?.items?.values?.forEach {
                    val data = arrayListOf<Pair<Long, Long>>()
                    it.take(k).forEach {
                        data.add(Pair(it.hour?.toLong() ?: 0L, it.counter ?: 0L))
                    }
                    result.add(data)
                }
                emit(result)
                setLoadingAsync(false)
            }.getOrElse {
                it.printStackTrace()
                setExceptionAsync(it.mapToCleanException())
            }
        }
    }

    val songs: LiveData<List<SongItem>> = _sectionChart.switchMap {
        liveData(defaultDispatcher) {
            runCatching {
                it.items?.take(k)?.let {
                    emit(it)
                }
            }
        }
    }
}
