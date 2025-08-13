package com.app.lingotales.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.lingotales.presentation.choose.ChooseScreen
import com.app.lingotales.presentation.home.HomeScreen
import com.app.lingotales.presentation.home.detail.HomeDetailScreen
import com.app.lingotales.presentation.home.detail.HomeDetailUiModel
import com.app.lingotales.presentation.login.LoginScreen
import com.app.lingotales.ui.theme.black
import com.app.lingotales.util.extension.navTypeOf
import com.app.tinytales.presentation.onboarding.OnboardingRoute
import kotlin.reflect.typeOf

@Composable
fun NavigationGraph(
    navController: NavHostController, startDestination: Destination, modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        }) {
        composable<Destination.Empty> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(black),
            )
        }

        composable<Destination.Onboarding> {
            OnboardingRoute(
                navigateToLoginScreen = {
                    navController.navigate(Destination.Login)
                })
        }

        composable<Destination.Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Destination.Choose)
                })
        }

        composable<Destination.Choose> {
            ChooseScreen(
                onCategorySelected = { type ->
                    navController.navigate(Destination.Home(type))
                }
            )
        }

        composable<Destination.Home> { backStackEntry ->
            val args = backStackEntry.toRoute<Destination.Home>()
            HomeScreen(
                type = args.type,
                onBackPressed = { navController.popBackStack() },
                navigateDetail = { model ->
                    navController.navigate(Destination.HomeDetail(model))
                }
            )
        }

        composable<Destination.HomeDetail>(
            typeMap = mapOf(typeOf<HomeDetailUiModel>() to navTypeOf<HomeDetailUiModel>())
        ) { backStackEntry ->
            val model: Destination.HomeDetail = backStackEntry.toRoute()
            HomeDetailScreen(
                uiModel = model.model,
                onBackPressed = { navController.popBackStack() }
            )
        }

    }
}