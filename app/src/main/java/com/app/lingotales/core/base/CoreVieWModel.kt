package com.app.lingotales.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.core.network.ApiException
import com.app.lingotales.core.network.RestResult
import com.app.sitaxi.core.event.DefaultUiEventPublisher
import com.app.sitaxi.core.event.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class CoreViewModel(
    private val eventPublisher: DefaultUiEventPublisher,
) : ViewModel() {

    private val _eventsFlow = MutableSharedFlow<UiEvent>()
    val eventsFlow: SharedFlow<UiEvent> = _eventsFlow.asSharedFlow()

    private var isLoadingShown = false

    fun <T> executeUseCase(
        result: Flow<RestResult<T>>,
        onSuccess: (suspend (T) -> Unit),
        onFailure: (suspend (throwable: ApiException) -> Unit) = { renderSiTaxiError(it) }
    ) {
        viewModelScope.launch {
            result.collectLatest { result ->
                when (result) {
                    is RestResult.Success -> {
                        onSuccess(result.data)
                    }

                    is RestResult.Failure -> {
                        when (val throwable = result.throwable) {
                            //is SiTaxiException -> onFailure.invoke(throwable)
                            //is ApiException -> renderApiError(throwable)
                            is ApiException -> onFailure.invoke(throwable)
                            else -> renderUnhandledError(throwable)
                        }

                    }

                    is RestResult.Loading -> {
                        handleLoading(result.isShowing)
                    }
                }
            }
        }
    }

    open fun renderUnhandledError(error: Throwable?) {
        error?.let {
            eventPublisher.publishUiEvent(
                UiEvent.ErrorEvent(
                    error.message ?: "teknik bir hata oluştu"
                )
            )
        }

        Timber.tag(this::class.java.name).d(error.toString())

    }

    open fun renderApiError(error: ApiException) {
        eventPublisher.publishUiEvent(
            UiEvent.ErrorEvent(
                message = error.message ?: "teknik bir hata oluştu"
            )
        )

        Timber.tag(this::class.java.name).d(error.message.toString())

    }

    open fun renderSiTaxiError(error: Throwable?) {
        error?.let {
            eventPublisher.publishUiEvent(
                UiEvent.ErrorEvent(
                    message = error.message ?: "teknik bir hataaa"
                )
            )
        }

        Timber.tag(this::class.java.name).d(error.toString())

    }


    open fun handleLoading(showLoading: Boolean) {
        if (isLoadingShown != showLoading) {
            isLoadingShown = showLoading
            eventPublisher.publishUiEvent(UiEvent.LoadingEvent(showLoading))
        }
    }

    fun sendErrorEvent(event: UiEvent.ErrorEvent) {
        eventPublisher.publishUiEvent(event)
    }
}