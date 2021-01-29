package com.bt.mp3.domain.repository

import com.bt.mp3.entity.Section

interface SectionRepository {

    suspend fun getSection(type: String): Section
}