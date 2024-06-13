package com.brainxtech.todolist.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.brainxtech.todolist.R
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.utils.Constants
import com.brainxtech.extensions.utils.Constants.ONE
import com.brainxtech.extensions.utils.Constants.ZERO_FLOAT
import com.brainxtech.extensions.utils.Constants.ZERO_TWO_FIVE_FLOAT

@Composable
fun CustomBasicTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color,
    textStyle: TextStyle,
    hintTextColor: Color?=null,
    hintTextStyle:TextStyle = basicEditTextHintStyle(hintTextColor?: MaterialTheme.colorScheme.onSecondaryContainer),
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int?=null,
    maxLines:Int?=null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction=ImeAction.None,
    keyboardActions: KeyboardActions=KeyboardActions(),
    onValueChange: (String) -> Unit,
){
    BasicTextField(
        modifier= modifier
            .fillMaxWidth(),
        value = text,
        cursorBrush = SolidColor(cursorBrush),
        onValueChange = {
            if (maxLength!=null && it.length>maxLength) return@BasicTextField
            onValueChange(it)
        },
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Password) KeyboardCapitalization.None else KeyboardCapitalization.Sentences,
            autoCorrect = false,
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines?:ONE,
        maxLines = if (singleLine) ONE else maxLines?:Int.MAX_VALUE,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (text.isEmpty()) {
                    placeHolderText?.let {
                        Text(
                            modifier = Modifier
                                .padding(zeroSize()),
                            text = it,
                            textAlign = TextAlign.Start,
                            style = hintTextStyle,
                        )
                    }
                }
            }
            innerTextField()
        })
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color,
    textStyle: TextStyle,
    hintTextColor: Color?=null,
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int?=null,
    maxLines:Int?=null,
    hintTextStyle: TextStyle= basicEditTextHintStyle(textColor = hintTextColor?:MaterialTheme.colorScheme.onSecondaryContainer),
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction=ImeAction.None,
    keyboardActions: KeyboardActions=KeyboardActions(),
    onValueChange: (String) -> Unit,
    paddingLeadingIconEnd: Dp = zeroSize(),
    paddingTrailingIconStart: Dp = zeroSize(),
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
) {
    Row(modifier = modifier
        .padding(vertical = editTextVerticalPadding(), horizontal = editTextHorizontalPadding()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        if (leadingIcon!=null)
            leadingIcon()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = paddingLeadingIconEnd, end = paddingTrailingIconStart)
        ) {
            CustomBasicTextField(
                text = text,
                cursorBrush = cursorBrush,
                textStyle = textStyle,
                placeHolderText = placeHolderText,
                hintTextColor = hintTextColor,
                maxLength = maxLength,
                keyboardType = keyboardType,
                singleLine = singleLine,
                minLines = minLines,
                maxLines = maxLines,
                hintTextStyle = hintTextStyle,
                imeAction = imeAction,
                keyboardActions = keyboardActions,
                onValueChange = { onValueChange(it) }
            )
        }
        if (trailingIcon != null)
            trailingIcon()
    }

}

@Composable
fun TrailingIconEditText(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText: String,
    cardBackgroundColor: Color=Color.White,
    keyboardType: KeyboardType=KeyboardType.Text,
    imeAction: ImeAction=ImeAction.None,
    keyboardActions: KeyboardActions=KeyboardActions(),
    trailingIcon: (@Composable() () -> Unit),
    onValueChange: (String) -> Unit,
    onTrailingIconClicked:(()->Unit)?=null
){
    ConstraintLayout(modifier = modifier
        .fillMaxWidth()
        .height(simpleEditTextHeight())
        .clip(simpleEditTextRoundedShape())
        .background(TodoListAppColors.cardBackground)
        .border(
            DimenDp.BorderWidthHalf_dp,
            color = TodoListAppColors.primaryColor.copy(alpha = ZERO_TWO_FIVE_FLOAT),
            shape = simpleEditTextRoundedShape()
        ),
        ) {
        val (editText,trailing)=createRefs()
        CustomBasicTextField(
            text = text,
            cursorBrush = MaterialTheme.colorScheme.onSecondaryContainer,
            textStyle = searchBarTextStyle(),
            placeHolderText = placeHolderText,
            keyboardType = keyboardType,
            keyboardActions = keyboardActions,
            imeAction = imeAction,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.constrainAs(editText){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                linkTo(start = parent.start,end= trailing.start, bias = ZERO_FLOAT, startMargin = DimenDp.SmallPadding ,endMargin =  DimenDp.ExtraSmallMargin)
                width = Dimension.fillToConstraints
            }
        )
        Box(
            modifier = Modifier
                .size(iconCircularButtonSize())
                .background(cardBackgroundColor, shape = CircleShape)
                .clip(CircleShape)
                .shadow(
                    DimenDp.defaultCardElevation,
                    shape = shadowShape(),
                    spotColor = cardBackgroundColor,
                    ambientColor = Color.Gray
                )
                .constrainAs(trailing) {
                    end.linkTo(parent.end, margin = DimenDp.SmallPadding)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickableWithoutRipple {
                    if (onTrailingIconClicked != null) {
                        onTrailingIconClicked()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            trailingIcon()
        }
    }
}

@Composable
fun SimpleEditText(
    modifier: Modifier = Modifier.fillMaxWidth().height(simpleEditTextHeight()),
    text: String,
    placeHolderText: String?=null,
    backgroundColor :Color = TodoListAppColors.cardBackground,
    shape:RoundedCornerShape = simpleEditTextRoundedShape(),
    textStyle: TextStyle=simpleTextStyle(),
    hintTextColor : Color=MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .8f),
    hintTextStyle: TextStyle=basicEditTextHintStyle(hintTextColor?: MaterialTheme.colorScheme.onSecondaryContainer),
    maxLength:Int?=null,
    keyboardType: KeyboardType=KeyboardType.Text,
    imeAction: ImeAction=ImeAction.None,
    keyboardActions: KeyboardActions=KeyboardActions(),
    singleLine: Boolean=true,
    minLines: Int?=null,
    maxLines: Int?=null,
    onValueChange: (String) -> Unit,
){
    CustomTextField(
        modifier = modifier
            .clip(simpleEditTextRoundedShape())
            .background(backgroundColor, shape = shape),
        text = text,
        placeHolderText=placeHolderText,
        maxLength = maxLength,
        cursorBrush = MaterialTheme.colorScheme.onPrimaryContainer,
        textStyle =textStyle ,
        hintTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .8f),
        onValueChange = {onValueChange(it)},
        keyboardType = keyboardType,
        imeAction = imeAction,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        hintTextStyle = hintTextStyle,
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText: String,
    onValueChange: (String) -> Unit,
) {
    CustomTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(simpleEditTextHeight())
            .clip(simpleEditTextRoundedShape())
            .background(TodoListAppColors.cardBackground),
        text = text,
        placeHolderText = placeHolderText,
        cursorBrush = MaterialTheme.colorScheme.onSecondaryContainer,
        textStyle = simpleTextStyle(),
        hintTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        onValueChange = {
            onValueChange(it)
        },
        keyboardType = KeyboardType.Text,
        paddingTrailingIconStart = editTextIconPadding(),
        paddingLeadingIconEnd = editTextIconPadding(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                tint = TodoListAppColors.drawerSelectedBackgroundColor,
                contentDescription = Constants.EMPTY_STRING
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                tint = TodoListAppColors.drawerSelectedBackgroundColor,
                contentDescription = Constants.EMPTY_STRING
            )
        }
    )
}