package com.app.lingotales.navigation

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
    data object Home : Destination()

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