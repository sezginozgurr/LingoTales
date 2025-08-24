package com.app.lingotales.presentation.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.R
import com.app.lingotales.core.datastore.PreferencesKeys
import com.app.lingotales.core.datastore.PreferencesManager
import com.app.lingotales.core.network.RestResult
import com.app.lingotales.data.service.LingoService
import com.app.lingotales.util.extension.safeApiCallWithRestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class ChooseUiState(
    val isLoading: Boolean = false,
    val selectedCategory: String? = null,
    val categories: List<CategoryUiModel> = emptyList(),
    val userName: String? = null
)

sealed class ChooseUiEvent {
    data class SelectCategory(val category: String) : ChooseUiEvent()
    data object NavigateToHome : ChooseUiEvent()
}

@HiltViewModel
class ChooseViewModel @Inject constructor(
    private val lingoService: LingoService,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    init {
        getCategories()
        loadUserName()
    }

    private val _uiState = MutableStateFlow(ChooseUiState(isLoading = true))
    val uiState: StateFlow<ChooseUiState> = _uiState.asStateFlow()

    private fun getCategories() {
        viewModelScope.launch {
            try {
                val result = safeApiCallWithRestResponse {
                    lingoService.getMobileCategories()
                }
                
                when (result) {
                    is RestResult.Success -> {
                        val categories = result.data.categories.map { category ->
                            CategoryUiModel(
                                id = category.id,
                                title = category.name,
                                description = category.description,
                                imageUrl = category.imageUrl,
                                imageRes = null,
                                type = CategoryType.MASAL
                            )
                        }
                        
                        _uiState.value = _uiState.value.copy(
                            categories = categories,
                            isLoading = false
                        )
                        
                    }
                    is RestResult.Failure -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }
                    is RestResult.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "API çağrısında hata: ${e.message}")
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    private fun loadUserName() {
        viewModelScope.launch {
            try {
                val name = preferencesManager.getString(PreferencesKeys.USER_NAME).first()
                _uiState.value = _uiState.value.copy(userName = name)
            } catch (e: Exception) {
                Timber.e(e, "Kullanıcı adı alınırken hata: ${e.message}")
            }
        }
    }

    /* fun onEvent(event: ChooseUiEvent) {
        when (event) {
            is ChooseUiEvent.SelectCategory -> {
                _uiState.value = _uiState.value.copy(selectedCategory = event.category)
            }

            is ChooseUiEvent.NavigateToHome -> {
                // Navigasyon tetiklenebilir
            }
        }
    } */
}
