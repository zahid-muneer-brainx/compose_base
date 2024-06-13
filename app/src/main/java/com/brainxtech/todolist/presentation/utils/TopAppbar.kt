package com.brainxtech.todolist.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ONE

@Composable
fun AppBar(
    modifier: Modifier,
    title: String,
    leadingIcon: Int,
    trailingIconChecked: Boolean=false,
    showBadge: Boolean=false,
    trailingIconContent: (@Composable () -> Unit)?=null,
    leadingIconClicked: () -> Unit,
    trailingIconClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .padding(vertical = DimenDp.SmallPadding, horizontal = DimenDp.DefaultPadding)
    ) {
        val (leading, text, trailing) = createRefs()
        Icon(
            painter = painterResource(id = leadingIcon),
            contentDescription = EMPTY_STRING,
            tint = Color.White,
            modifier = Modifier
                .wrapContentSize()
                .clickableWithoutRipple { leadingIconClicked() }
                .constrainAs(leading) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        )
        BodyLargeText(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(text) {
                    top.linkTo(leading.top)
                    start.linkTo(leading.end, margin = DimenDp.SmallPadding)
                    end.linkTo(trailing.start, margin = DimenDp.SmallPadding)
                    bottom.linkTo(leading.bottom)
                    width = Dimension.fillToConstraints
                },
            text = title.uppercase(),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onPrimary
        )
       val trailingIconModifier  = Modifier
           .constrainAs(trailing) {
               end.linkTo(parent.end)
               top.linkTo(parent.top)
               bottom.linkTo(parent.bottom)
           }
           .clickableWithoutRipple { trailingIconClicked() }
        Box(modifier = trailingIconModifier){
            if (trailingIconContent != null) {
                trailingIconContent()
            }
        }
    }
}

@Composable
fun AppBar(
    modifier: Modifier,
    title: String,
    leadingIcon: Int,
    trailingBtnText: Int?=null,
    leadingIconClicked: () -> Unit,
    trailingButtonClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .padding(vertical = DimenDp.SmallPadding, horizontal = DimenDp.DefaultPadding)
    ) {
        val (leading, text, trailing) = createRefs()
        Icon(
            painter = painterResource(id = leadingIcon),
            contentDescription = EMPTY_STRING,
            tint = Color.White,
            modifier = Modifier
                .wrapContentSize()
                .clickableWithoutRipple { leadingIconClicked() }
                .constrainAs(leading) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        )
        BodyLargeText(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(text) {
                    top.linkTo(leading.top)
                    start.linkTo(leading.end, margin = DimenDp.SmallPadding)
                    end.linkTo(trailing.start, margin = DimenDp.SmallPadding)
                    bottom.linkTo(leading.bottom)
                    width = Dimension.fillToConstraints
                },
            text = title,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onPrimary
        )
        val trailingModifier  = Modifier
            .constrainAs(trailing) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .clickableWithoutRipple { trailingButtonClicked() }

        trailingBtnText?.let {
          BodyLargeText(modifier =trailingModifier.wrapContentSize() , text = stringResource(id = it), textAlign = TextAlign.End, color = Color.White, maxLines = ONE)
        }

    }
}