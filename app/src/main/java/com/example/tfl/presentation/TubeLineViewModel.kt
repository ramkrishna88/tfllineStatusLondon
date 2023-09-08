package com.example.tfl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfl.domain.*
import com.example.tfl.utils.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TubeLineViewModel @Inject constructor(
    private val getTubeLineStatusUseCase: GetTubeLineStatusUseCase
) : ViewModel() {

    private val _tubeLineStatusState = MutableStateFlow<TubeLineStatusState>(TubeLineStatusState.Loading)
    val tubeLineStatusState: StateFlow<TubeLineStatusState> = _tubeLineStatusState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = getTubeLineStatusUseCase()
                _tubeLineStatusState.value = TubeLineStatusState.Success(response)
            } catch (e: Exception) {
                val errorMessage = "Failed to fetch tube line status: ${e.message}"
                _tubeLineStatusState.value = TubeLineStatusState.Error(errorMessage)
            }
        }
    }
}

