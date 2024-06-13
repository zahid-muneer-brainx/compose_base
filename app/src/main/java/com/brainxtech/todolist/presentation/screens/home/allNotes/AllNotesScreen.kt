package com.brainxtech.todolist.presentation.screens.home.allNotes

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.brainxtech.extensions.compose_ui_utils.ComposableLifecycle
import com.brainxtech.extensions.extension.getAppsActivity
import com.brainxtech.extensions.extension.openSettings
import com.brainxtech.todolist.datasource.appUtils.NotificationPermissionTextProvider
import com.brainxtech.todolist.presentation.events.BasicScreenEvent
import com.brainxtech.todolist.presentation.states.BasicScreenState
import com.brainxtech.todolist.presentation.uiEvents.BasicScreenUiEvent
import com.brainxtech.todolist.presentation.utils.CustomDatePicker
import com.brainxtech.todolist.presentation.utils.CustomTimePicker
import com.brainxtech.todolist.presentation.utils.DimenDp
import com.brainxtech.todolist.presentation.utils.PermissionDialog
import com.brainxtech.todolist.presentation.utils.topRoundedCornerBackgroundShape
import com.brainxtech.todolist.presentation.utils.zeroSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    navBackStackEntry: NavBackStackEntry,
    dataState: StateFlow<BasicScreenState>,
    uiEvents: Flow<BasicScreenUiEvent>,
    onEvent: (BasicScreenEvent) -> Unit,
){
    val state by dataState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var showNewTaskBottomSheet by remember { mutableStateOf(false) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimerPickerDialog by remember { mutableStateOf(false) }
    var showFilterBottomSheet by remember { mutableStateOf(false) }
    var showFilterDatePickerDialog by remember { mutableStateOf(false) }
    var showNotificationDialog by remember { mutableStateOf(false) }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {

            }

            else -> Unit
        }
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showNotificationDialog = false
        } else {
            // Show dialog
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && showNotificationDialog) {
        ShowNotificationDialog(
            isPermanentlyDeclined = context.getAppsActivity()
                ?.shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
                ?: false,
            onLaunchPermission = {
                showNotificationDialog = false
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            },
            onDismiss = {
                showNotificationDialog = false
            })
    }

    LaunchedEffect(key1 = uiEvents) {
        uiEvents.collect {
            when (it) {
                else->Unit
            }
        }
    }

    if (showDatePickerDialog) {
        CustomDatePicker(
            selectedDate = 0L,
            onDateSelected = { },
            onCancel = { scope.launch { showDatePickerDialog=false }}
        )
    }

    if (showTimerPickerDialog) {
        CustomTimePicker(
            selectedTime = 0L,
            onTimeSelected = { },
            onCancel = {  scope.launch { showTimerPickerDialog=false } }
        )
    }


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {

        },
        sheetPeekHeight = zeroSize(),
        sheetContainerColor = Color.White,
        sheetTonalElevation = DimenDp.ExtraSmallPadding,
        sheetSwipeEnabled = true,
        modifier = Modifier
            .background(
                color = Color.White,
                shape = topRoundedCornerBackgroundShape()
            )
            .clip(topRoundedCornerBackgroundShape())
    ) {

    }
}
@Composable
private fun ShowNotificationDialog(isPermanentlyDeclined:Boolean, onLaunchPermission:(Boolean)->Unit, onDismiss: (Boolean) -> Unit){
    val context = LocalContext.current
    PermissionDialog(
        permissionTextProvider = NotificationPermissionTextProvider(),
        isPermanentlyDeclined = isPermanentlyDeclined,
        onDismiss = {
            onDismiss(true)
        },
        onOkClick = {
            onLaunchPermission(true)
        },
        onGoToAppSettingsClick = { context.openSettings() })
}