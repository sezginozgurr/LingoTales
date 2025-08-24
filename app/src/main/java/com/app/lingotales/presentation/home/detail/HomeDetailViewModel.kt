package com.app.lingotales.presentation.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.core.network.RestResult
import com.app.lingotales.data.model.BookDetail
import com.app.lingotales.data.model.Page
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
class HomeDetailViewModel @Inject constructor(
    private val lingoService: LingoService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeDetailUiState())
    val uiState: StateFlow<HomeDetailUiState> = _uiState.asStateFlow()

    fun getBookDetail(bookId: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                
                val result = safeApiCallWithRestResponse {
                    lingoService.getBookDetail(bookId)
                }
                
                when (result) {
                    is RestResult.Success -> {
                        val book = result.data.book
                        val pages = result.data.pages
                        
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            book = book,
                            pages = pages,
                            error = null
                        )
                        
                        Timber.d("Kitap detayı başarıyla çekildi: ${book.title}, ${pages.size} sayfa")
                    }
                    is RestResult.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = result.throwable.message ?: "Bilinmeyen hata"
                        )
                        Timber.e("Kitap detayı çekilirken hata: ${result.throwable.message}")
                    }
                    is RestResult.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                        Timber.d("Kitap detayı yükleniyor...")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Bilinmeyen hata"
                )
                Timber.e(e, "getBookDetail çağrısında hata: ${e.message}")
            }
        }
    }
}

data class HomeDetailUiState(
    val isLoading: Boolean = false,
    val book: BookDetail? = null,
    val pages: List<Page> = emptyList(),
    val error: String? = null
)