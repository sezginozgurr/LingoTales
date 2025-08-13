package com.app.lingotales.navigation

import com.app.lingotales.presentation.choose.CategoryType
import com.app.lingotales.presentation.home.detail.HomeDetailUiModel
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Empty : Destination()

    @Serializable
    data object Onboarding : Destination()

    @Serializable
    data object Login : Destination()

    @Serializable
    data class Home(val type: CategoryType) : Destination()

    @Serializable
    data class HomeDetail(val model: HomeDetailUiModel) : Destination()

    @Serializable
    data object Choose : Destination()

    @Serializable
    data object First : Destination()

    @Serializable
    data object Second : Destination()

    @Serializable
    data object Third : Destination()

    @Serializable
    data object Fourth : Destination()

    @Serializable
    data object Map : Destination()

}