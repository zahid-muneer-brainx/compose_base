package com.brainxtech.todolist.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.brainxtech.todolist.R
import com.brainxtech.todolist.presentation.navigation.RootNavRoutes
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.todolist.presentation.utils.DimenDp
import com.brainxtech.extensions.compose_ui_utils.navigateToNextScreen
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navHostController: NavHostController){
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        scope.launch {
            delay(3000L)
            navHostController.navigateToNextScreen<Unit>(RootNavRoutes.Home.route)
        }
    }
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.iv_logo), contentDescription = EMPTY_STRING)

        Text(
            modifier = Modifier.wrapContentSize().padding(vertical = DimenDp.SmallPadding),
            text = stringResource(id = R.string.app_name),
            color = TodoListAppColors.greyTextColor5,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Companion.W600)
        )
    }
}