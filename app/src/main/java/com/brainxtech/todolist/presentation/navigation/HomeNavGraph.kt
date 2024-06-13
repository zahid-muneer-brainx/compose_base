package com.brainxtech.todolist.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.brainxtech.extensions.compose_ui_utils.horizontallyAnimatedComposable
import com.brainxtech.todolist.presentation.screens.home.allNotes.HomeScreen
import com.brainxtech.todolist.presentation.viewmodels.BasicViewModel
import org.koin.androidx.compose.koinViewModel

enum class HomeRoutes(val route: String){
    ALL_NOTES("all_notes"),
    CALENDAR("calendar"),
    CATEGORY("category"),
    FEEDBACK("feedback"),
    FAQ("faqs"),
    SETTINGS("settings")
}

fun NavGraphBuilder.homeNavHostGraph(navController: NavHostController){
    navigation(
        startDestination = HomeRoutes.ALL_NOTES.route,
        route = RootNavRoutes.Home.route,
    ) {
        horizontallyAnimatedComposable(HomeRoutes.ALL_NOTES.route){
            val viewModel = koinViewModel<BasicViewModel>()
            HomeScreen(navController,it,viewModel.state,viewModel.eventFlow,
                onEvent = {viewModel.onEventUpdate(it)},
            )
        }
    }
}