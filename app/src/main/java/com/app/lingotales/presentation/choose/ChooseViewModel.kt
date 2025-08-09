package com.app.lingotales.presentation.choose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChooseUiState(
    val isLoading: Boolean = false,
    val selectedCategory: String? = null
)

sealed class ChooseUiEvent {
    data class SelectCategory(val category: String) : ChooseUiEvent()
    data object NavigateToHome : ChooseUiEvent()
}

class ChooseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChooseUiState())
    val uiState: StateFlow<ChooseUiState> = _uiState.asStateFlow()

    fun onEvent(event: ChooseUiEvent) {
        when (event) {
            is ChooseUiEvent.SelectCategory -> {
                _uiState.value = _uiState.value.copy(selectedCategory = event.category)
            }

            is ChooseUiEvent.NavigateToHome -> {

            }
        }
    }
} 