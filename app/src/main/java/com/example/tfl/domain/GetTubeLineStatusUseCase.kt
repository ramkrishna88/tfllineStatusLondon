package com.example.tfl.domain

import com.example.tfl.data.model.*
import com.example.tfl.data.repository.*
import javax.inject.Inject

class GetTubeLineStatusUseCase @Inject constructor(
    private val repository: TubeLineRepository
) {
    suspend operator fun invoke(): List<TubeLineStatus> {
        return repository.getTubeLineStatus()
    }
}
