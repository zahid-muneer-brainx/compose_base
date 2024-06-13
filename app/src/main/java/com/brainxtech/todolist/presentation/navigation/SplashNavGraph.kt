package com.brainxtech.todolist.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.brainxtech.todolist.presentation.screens.splash.SplashScreen
import com.brainxtech.extensions.compose_ui_utils.horizontallyAnimatedComposable

enum class SplashScreenRoutes(val route: String){
    SPLASH("splash_screen"),
}


fun NavGraphBuilder.splashNavGraph(navController: NavHostController){
    navigation(
        startDestination = SplashScreenRoutes.SPLASH.route,
        route = RootNavRoutes.Splash.route,
    ) {
        horizontallyAnimatedComposable(SplashScreenRoutes.SPLASH.route){
            SplashScreen(navHostController = navController)
        }
    }
}