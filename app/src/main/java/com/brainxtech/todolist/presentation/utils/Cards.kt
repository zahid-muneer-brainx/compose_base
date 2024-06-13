package com.brainxtech.todolist.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING

@Composable
fun SimpleCard(
    modifier: Modifier,
    cardColor: Any = TodoListAppColors.cardBackground,
    cardCornerRadius: Dp = cardCornerRadius(),
    content: @Composable () -> Unit
){
    val cardModifier = if (cardColor is List<*>) {
        modifier.background(
            brush = Brush.horizontalGradient(cardColor as List<Color>),
            shape = RoundedCornerShape(cardCornerRadius)
        )
    } else {
        modifier.background(
            cardColor as Color,
            shape = RoundedCornerShape(size = cardCornerRadius)
        )
    }
    Box(
        modifier = modifier
            .then(
                if (cardColor is List<*>) {
                    modifier.background(
                        brush = Brush.horizontalGradient(cardColor as List<Color>),
                        shape = RoundedCornerShape(cardCornerRadius)
                    )
                } else {
                    modifier.background(
                        cardColor as Color,
                        shape = RoundedCornerShape(size = cardCornerRadius)
                    )
                }
            )
        ,contentAlignment = Alignment.Center
    ){
        content()
    }
}

@Composable
fun SimpleCard(
    modifier: Modifier,
    cardColor: Any=MaterialTheme.colorScheme.surface,
    borderColor: Color= Color.White,
    borderWidth:Dp = DimenDp.BorderWidthPointTwo_dp,
    cardCornerRadius:Dp = cardCornerRadius(),
    content: @Composable () -> Unit
){
    val cardModifier = if (cardColor is List<*>) {
        modifier.background(
            brush = Brush.horizontalGradient(cardColor as List<Color>),
            shape = RoundedCornerShape(cardCornerRadius)
        )
    } else {
        modifier.background(
            cardColor as Color,
            shape = RoundedCornerShape(size = cardCornerRadius)
        )
    }
    Box(
        modifier = cardModifier.border(
            width = borderWidth,
            color = borderColor,
            shape = RoundedCornerShape(size = cardCornerRadius)
        ),
        contentAlignment = Alignment.Center
    ){
        content()
    }
}

@Composable
fun SimpleIconCard(icon: Int, isChecked: Boolean = false,onClicked:()->Unit) {
    SimpleCard(
        modifier = Modifier.size(iconCardSize()).clickableWithoutRipple { onClicked() },
        cardColor = if (isChecked) TodoListAppColors.blueBackgroundColor else Color.White,
        borderColor = if (isChecked) TodoListAppColors.primaryColor else Color.White,
        borderWidth = DimenDp.BorderWidthHalf_dp,
        cardCornerRadius = categoryIconCardCornerRadius()
    ) {
        Image(painter = painterResource(id = icon), contentDescription = EMPTY_STRING)
    }
}