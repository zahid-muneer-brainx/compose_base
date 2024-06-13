package com.brainxtech.todolist.presentation.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.brainxtech.todolist.R
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING

@Composable
fun CustomCheckbox(
    isChecked: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    SimpleCard(modifier = modifier
        .size(smallIconSize())
        .clickableWithoutRipple {
            if (onClick != null) {
                onClick()
            }
        },
        cardColor = if (isChecked) TodoListAppColors.primaryColor else Color.Transparent,
        borderColor = TodoListAppColors.primaryColor,
        cardCornerRadius = checkBoxRadius(),
        borderWidth = DimenDp.BorderWidthHalf_dp
    ) {
        AnimatedVisibility(visible = isChecked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_tick),
                tint = Color.White, contentDescription = EMPTY_STRING
            )
        }
    }
}

@Composable
fun CustomRadioButton(
    isChecked: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Box(modifier = modifier
        .size(smallIconSize())
        .clickableWithoutRipple {
            if (onClick != null) {
                onClick()
            }
        } ){
        AnimatedVisibility(visible = isChecked,) {
            Image(
                painter = painterResource(id = R.drawable.ic_checked_radio_button),
                contentDescription = EMPTY_STRING
            )
        }
        AnimatedVisibility(visible = !isChecked) {
            Image(
                painter = painterResource(id = R.drawable.ic_unchecked_radio_button),
                contentDescription = EMPTY_STRING
            )
        }
    }
}