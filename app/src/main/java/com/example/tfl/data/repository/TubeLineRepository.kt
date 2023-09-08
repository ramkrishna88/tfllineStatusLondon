package com.example.tfl.data.repository

import com.example.tfl.data.model.*

interface TubeLineRepository {
    suspend fun getTubeLineStatus(): List<TubeLineStatus>
}
