package com.bt.mp3.data.mock.network

import com.bt.mp3.data.mock.factory.FileResourcesUtils
import com.bt.mp3.data.model.SectionResponseEntity
import com.bt.mp3.data.network.SectionApi
import com.google.gson.Gson

class SectionApiMock : SectionApi {

    override suspend fun getSection(type: String): SectionResponseEntity {
        val data = FileResourcesUtils().getDataStr("real_time_chart_sample.json")

        return Gson().fromJson(data, SectionResponseEntity::class.java)
    }
}
