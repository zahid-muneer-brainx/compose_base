package com.brainxtech.todolist.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.brainxtech.todolist.R
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ONE
import com.brainxtech.extensions.utils.Constants.ZERO_FLOAT

@Composable
fun SimpleChipWithIcon(
    modifier: Modifier = Modifier.defaultMinSize(
        minWidth = defaultChipWidth(),
        minHeight = defaultChipHeight()
    ).padding(vertical = DimenDp.XXSmallMargin),
    text: String,
    textColor: Color,
    chipColor: Color,
    chipBorderColor: Color,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    leadingIcon: (@Composable() () -> Unit)? = null,
    onCancelClicked: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .padding(horizontal = DimenDp.ExtraSmallPadding)
            .background(chipColor, shape = RoundedCornerShape(size = shipRadius()))
            .border(
                width = DimenDp.BorderWidthPointTwo_dp,
                color = chipBorderColor,
                shape = RoundedCornerShape(size = shipRadius())
            )
            .padding(horizontal = DimenDp.ExtraSmallPadding, vertical = DimenDp.XXSmallMargin)
    ) {
        val (leading, trailing) = createRefs()
        Row(
            modifier = Modifier.constrainAs(leading) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                linkTo(
                    start = parent.start,
                    end = trailing.start,
                    endMargin = DimenDp.ExtraSmallMargin,
                    bias = ZERO_FLOAT
                )
                width = Dimension.fillToConstraints
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DimenDp.ExtraSmallMargin)
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            Text(
                modifier = Modifier,
                text = text,
                style = textStyle,
                textAlign = TextAlign.Start,
                color = textColor,
                maxLines = ONE
            )
        }
        Icon(modifier = Modifier
            .constrainAs(trailing) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .size(xxSmallIconSize())
            .clickableWithoutRipple { onCancelClicked() },
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = EMPTY_STRING,
            tint = textColor
        )
    }
}

@Composable
fun SimpleTextChip(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    chipColor: Color = TodoListAppColors.greyBackgroundColor,
    chipBorderColor: Color = TodoListAppColors.greyBackgroundColor,
    onClicked: () -> Unit
) {
    SimpleCard(modifier = modifier
        .height(defaultChipHeight())
        .width(defaultChipWidth())
        .clickableWithoutRipple { onClicked() },
        cardColor = chipColor,
        borderColor = chipBorderColor,
        cardCornerRadius = shipRadius()
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = text,
            style = textStyle,
            textAlign = TextAlign.Start,
            color = textColor,
            maxLines = ONE
        )
    }
}