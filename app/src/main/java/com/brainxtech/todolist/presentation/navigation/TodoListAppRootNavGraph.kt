package com.brainxtech.todolist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

enum class RootNavRoutes(val route: String) {
    Splash("splash"),
    Home("home"),
    EditTask("update_task"),

}

@Composable
fun AppNavHostGraph(
    navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = RootNavRoutes.Splash.route
    ) {
        splashNavGraph(navController)
        homeNavHostGraph(navController)
    }
}