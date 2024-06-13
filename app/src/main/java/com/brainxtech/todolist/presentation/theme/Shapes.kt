package com.brainxtech.todolist.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
private fun DrawerShape()=RoundedCornerShape(8.dp)

@Composable
fun SelectedDrawerItemModifier()= Modifier
    .fillMaxWidth()
    .wrapContentHeight()
    .background(TodoListAppColors.drawerSelectedBackgroundColor, shape = DrawerShape())
    .clip(DrawerShape())

@Composable
fun UnSelectedDrawerItemModifier()= Modifier
    .fillMaxWidth()
    .wrapContentHeight()
    .background(Color.White)