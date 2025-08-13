package com.app.lingotales.presentation.onboarding

import androidx.annotation.RawRes

data class OnboardingPage(
    @RawRes val lottieRes: Int,
    val title: String,
    val description: String
)
