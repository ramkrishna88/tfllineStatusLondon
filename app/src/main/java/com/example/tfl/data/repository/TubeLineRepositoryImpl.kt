package com.example.tfl.data.repository

import com.example.tfl.data.api.*
import com.example.tfl.data.model.*
import javax.inject.Inject

class TubeLineRepositoryImpl @Inject constructor(
    private val apiService: TflApiService
) : TubeLineRepository {

    override suspend fun getTubeLineStatus(): List<TubeLineStatus> {
        return apiService.getTubeLineStatus()
    }
}
