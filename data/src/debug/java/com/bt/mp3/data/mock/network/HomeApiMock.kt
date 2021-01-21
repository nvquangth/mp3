package com.bt.mp3.data.mock.network

import com.bt.mp3.data.mock.factory.FileResourcesUtils
import com.bt.mp3.data.model.HomePageResponseEntity
import com.bt.mp3.data.network.HomeApi
import com.google.gson.Gson

class HomeApiMock : HomeApi {

    override suspend fun getHomePage(pageNumber: Int?): HomePageResponseEntity {
        val data = when (pageNumber) {
            1 -> FileResourcesUtils().getDataStr("home_page_data_sample.json")
            2 -> FileResourcesUtils().getDataStr("home_page_data_sample_2.json")
            3 -> FileResourcesUtils().getDataStr("home_page_data_sample_3.json")
            else -> FileResourcesUtils().getDataStr("home_page_data_sample_4.json")
        }

        return Gson().fromJson(data, HomePageResponseEntity::class.java)
    }
}
