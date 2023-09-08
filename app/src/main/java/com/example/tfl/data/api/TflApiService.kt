package com.example.tfl.data.api

import com.example.tfl.data.model.*
import retrofit2.http.*
import retrofit2.http.GET

interface TflApiService {
    @GET("Line/Mode/Tube/Status")
    suspend fun getTubeLineStatus(): List<TubeLineStatus>
}

