package com.app.lingotales.presentation.home

import kotlinx.serialization.Serializable

sealed interface HomeUiEvent {

    @Serializable
    data object NavigateVerificationOtp : HomeUiEvent
}
