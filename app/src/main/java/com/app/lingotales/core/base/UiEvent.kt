package com.app.sitaxi.core.event

sealed interface UiEvent {
    data class ErrorEvent(val message: String) : UiEvent
    data class LoadingEvent(val showLoading: Boolean) : UiEvent
    data object NavigateToLogin : UiEvent
    data object NoInternet : UiEvent
}

