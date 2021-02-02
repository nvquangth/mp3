package com.bt.mp3.data.network.retrofit

import com.bt.mp3.data.model.SectionResponseEntity
import retrofit2.http.GET

interface SectionApi {

    @GET
    suspend fun getSection(type: String): SectionResponseEntity
}
