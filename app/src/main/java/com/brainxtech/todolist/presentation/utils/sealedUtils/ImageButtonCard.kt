package com.brainxtech.todolist.presentation.utils.sealedUtils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

sealed class ImageButtonCard{
    data class IconButton(val resource:Int, val tint: Color?=null, val size: Dp) : ImageButtonCard()
    data class ImageButton(val resource:Int,val size: Dp) : ImageButtonCard()

}