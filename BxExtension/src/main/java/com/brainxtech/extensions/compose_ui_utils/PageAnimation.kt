package com.brainxtech.extensions.compose_ui_utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.brainxtech.extensions.utils.Constants.animDurationMillis


private typealias SlideDirection = AnimatedContentTransitionScope.SlideDirection

fun deleteItemAnimation() = shrinkVertically(
    animationSpec = tween(durationMillis = animDurationMillis),
    shrinkTowards = Alignment.Top
) + fadeOut()

fun NavGraphBuilder.horizontallyAnimatedComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        content = content,
        enterTransition =  {
            slideIntoContainer(SlideDirection.Left, animationSpec = tween(animDurationMillis))
        },
        exitTransition = {
            slideOutOfContainer(SlideDirection.Left, animationSpec = tween(animDurationMillis))
        },
        popEnterTransition = {
            slideIntoContainer(SlideDirection.Right, animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(SlideDirection.Right, animationSpec = tween(animDurationMillis))
        },
    )
}

fun NavGraphBuilder.horizontallyAnimatedComposableEnterOnly(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
){
    composable(
        route = route,
        content = content,
        exitTransition = {
            slideOutOfContainer(SlideDirection.Left, animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(SlideDirection.Right, animationSpec = tween(animDurationMillis))
        },
    )
}

fun NavGraphBuilder.horizontallyAnimatedComposableExitOnly(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
){
    composable(
        route = route,
        content = content,
        exitTransition = {
            slideOutOfContainer(SlideDirection.Left, animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(SlideDirection.Right, animationSpec = tween(animDurationMillis))
        },
    )
}

fun NavGraphBuilder.verticallyAnimatedComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        content = content,
        enterTransition = {
            slideIntoContainer(SlideDirection.Up, animationSpec = tween(animDurationMillis))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(animDurationMillis))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(SlideDirection.Down, animationSpec = tween(animDurationMillis))
        },
    )
}

fun NavGraphBuilder.verticallyAnimatedComposableEnterOnly(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        content = content,
        enterTransition = {
            slideIntoContainer(SlideDirection.Up, animationSpec = tween(animDurationMillis))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(animDurationMillis))
        },
    )
}

fun NavGraphBuilder.verticallyAnimatedComposableExitOnly(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        content = content,
        exitTransition = {
            fadeOut(animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(SlideDirection.Down, animationSpec = tween(animDurationMillis))
        },
    )
}
