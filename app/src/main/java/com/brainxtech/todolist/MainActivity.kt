package com.brainxtech.todolist

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.brainxtech.extensions.extension.showToast
import com.brainxtech.extensions.utils.Constants.NEGATIVE_ONE
import com.brainxtech.todolist.datasource.appUtils.BaseUiEvents
import com.brainxtech.todolist.datasource.appUtils.Events
import com.brainxtech.todolist.presentation.navigation.AppNavHostGraph
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.todolist.presentation.theme.TodoListAppTheme
import com.brainxtech.todolist.presentation.utils.GenericDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var showDialog  by  mutableStateOf(Pair<Boolean,Any>(first = false, second = NEGATIVE_ONE))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            fullScreen()
            val navController = rememberNavController()
            TodoListAppTheme {
                AppNavHostGraph(navController = navController)
                if (showDialog.first){
                    GenericDialog(
                        message = showDialog.second ,
                        onDismiss = { showDialog = Pair(false,NEGATIVE_ONE)},
                        onOkClick = {  showDialog = Pair(false,NEGATIVE_ONE)})
                }
            }
        }
        observeEvent()
    }

    private fun fullScreen(){
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(TodoListAppColors.tertiaryColor.toArgb(), TodoListAppColors.tertiaryColor.toArgb()),
            navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE)
        )
    }

    private fun observeEvent(){
        lifecycleScope.launch(Dispatchers.Main) {
            Events.baseEventFlow.collectLatest {
                when(it){
                    is BaseUiEvents.ShowToast->{
                        showToast(it.message)
                    }
                    is BaseUiEvents.ShowDialog->{
                        showDialog = Pair(true,it.message)
                    }
                    else->Unit
                }
            }
        }
    }
}


