package com.bt.mp3.domain.repository

import com.bt.mp3.entity.HomePage

interface HomeRepository {

    suspend fun getHomePage(pageNumber: Int): HomePage
}
