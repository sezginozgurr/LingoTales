package com.app.lingotales.presentation.choose

import androidx.lifecycle.ViewModel
import com.app.lingotales.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChooseUiState(
    val isLoading: Boolean = false,
    val selectedCategory: String? = null,
    val categories: List<CategoryUiModel> = emptyList()
)

sealed class ChooseUiEvent {
    data class SelectCategory(val category: String) : ChooseUiEvent()
    data object NavigateToHome : ChooseUiEvent()
}

class ChooseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ChooseUiState(
            categories = listOf(
                CategoryUiModel("masal", "Masallar", R.drawable.masallar),
                CategoryUiModel("hikaye", "Hikayeler", R.drawable.egitici),
                CategoryUiModel("egitici", "Eğitici Hikayeler", R.drawable.egitici),
                CategoryUiModel("hayvan", "Hayvan Hikayeleri", R.drawable.hayvan_hikayesi),
            )
        )
    )
    val uiState: StateFlow<ChooseUiState> = _uiState.asStateFlow()

    fun onEvent(event: ChooseUiEvent) {
        when (event) {
            is ChooseUiEvent.SelectCategory -> {
                _uiState.value = _uiState.value.copy(selectedCategory = event.category)
            }

            is ChooseUiEvent.NavigateToHome -> {
                // Navigasyon tetiklenebilir
            }
        }
    }
}
