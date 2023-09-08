package com.example.tfl.utils

import com.example.tfl.data.model.*

sealed class TubeLineStatusState {
    data object Loading : TubeLineStatusState()
    data class Success(val data: List<TubeLineStatus>) : TubeLineStatusState()
    data class Error(val message: String) : TubeLineStatusState()
}
