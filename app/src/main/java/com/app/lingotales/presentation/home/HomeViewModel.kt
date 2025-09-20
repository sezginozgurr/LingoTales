package com.app.lingotales.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.core.network.RestResult
import com.app.lingotales.data.model.Book
import com.app.lingotales.data.model.Category
import com.app.lingotales.data.model.CategoryBooks
import com.app.lingotales.data.service.LingoService
import com.app.lingotales.util.extension.safeApiCallWithRestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val lingoService: LingoService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getCategoryBooks(categoryId: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                
                val result = safeApiCallWithRestResponse {
                    lingoService.getCategoryBooks(categoryId)
                }
                
                when (result) {
                    is RestResult.Success -> {
                        val category = result.data.category
                        val books = result.data.books
                        
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            category = category,
                            books = books ?: emptyList(),
                            error = null
                        )
                        
                        Timber.d("Kitaplar başarıyla çekildi: ${books?.size} adet, Kategori: ${category?.name}")
                    }
                    is RestResult.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = result.throwable.message ?: "Bilinmeyen hata"
                        )
                        Timber.e("Kitaplar çekilirken hata: ${result.throwable.message}")
                    }
                    is RestResult.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                        Timber.d("Kitaplar yükleniyor...")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Bilinmeyen hata"
                )
                Timber.e(e, "getCategoryBooks çağrısında hata: ${e.message}")
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val category: CategoryBooks? = null,
    val books: List<Book> = emptyList(),
    val error: String? = null
)