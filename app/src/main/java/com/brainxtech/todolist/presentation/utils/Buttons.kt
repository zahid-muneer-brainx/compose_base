package com.brainxtech.todolist.presentation.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.todolist.presentation.theme.TodoListAppColors.cardBackground
import com.brainxtech.todolist.presentation.utils.sealedUtils.ImageButtonCard
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.utils.Constants
import com.brainxtech.extensions.utils.Constants.ZERO_FIVE_FLOAT

@Composable
fun RoundedBorderCenteredContentCorneredButton(
    buttonText:String,
    buttonColor: Color,
    textColor: Color,
    borderColor: Color?=null,
    isClickable:Boolean=true,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    modifier: Modifier,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: ()->Unit
){
    Button(
        onClick = { if (isClickable) onClickAction() else null},
        elevation = ButtonDefaults.buttonElevation(defaultElevation = zeroSize()),
        enabled = isClickable,
        modifier = modifier
            .fillMaxWidth()
            .height(buttonSize()),
        shape = RoundedCornerShape(buttonCornerRadius()),
        colors = ButtonDefaults.buttonColors(containerColor = if (isClickable) buttonColor else buttonColor.copy(alpha=disableButton())),
        border = BorderStroke(DimenDp.BorderWidthHalf_dp, borderColor?: Color.Transparent)
    ) {
        if (leadingIcon!=null)
            leadingIcon()
        Text(text = buttonText,
            color = textColor,
            style = textStyle,
            textAlign = TextAlign.Center
        )
        if (trailingIcon != null)
            trailingIcon()
    }
}

@Composable
fun GradientRoundedBorderCorneredButton(
    buttonText:String,
    gradientColor:List<Color>,
    textColor:Color,
    isClickable:Boolean=true,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    modifier: Modifier,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: ()->Unit
){
    val buttonModifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(buttonCornerRadius()))
    Button(
        onClick = { if (isClickable) onClickAction() else null},
        elevation = ButtonDefaults.buttonElevation(defaultElevation = zeroSize()),
        enabled = isClickable,
        modifier = Modifier
            .then(if (isClickable){
                buttonModifier.background(
                    brush = Brush.horizontalGradient(gradientColor),
                    shape = RoundedCornerShape(buttonCornerRadius())
                )
            }else{
                buttonModifier.background(
                    color = cardBackground,
                    shape = RoundedCornerShape(buttonCornerRadius())
                )
            })
        ,
        shape = RoundedCornerShape(buttonCornerRadius()),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        if (leadingIcon!=null)
            leadingIcon()
        Text(text = buttonText,
            color = textColor,
            style = textStyle,
            textAlign = TextAlign.Center
        )
        if (trailingIcon != null)
            trailingIcon()
    }
}

@Composable
fun RoundedBorderRightAlignedContentCorneredButton(
    buttonText:String,
    buttonColor: Color,
    textColor: Color,
    modifier: Modifier= Modifier
        .fillMaxWidth()
        .height(standardButtonSize()),
    leadingIcon: Int,
    onClickAction: ()->Unit
){
    SimpleCard(
        modifier = modifier,
        cardColor = buttonColor,
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = DimenDp.MediumPadding, end = DimenDp.SmallPadding)
            .clickableWithoutRipple { onClickAction() }) {
            val (icon,title,menu)=createRefs()
            Image(painter = painterResource(id = leadingIcon), contentDescription = Constants.EMPTY_STRING,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    })
            DisplayMediumText(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    linkTo(start = icon.end, end = menu.start, bias = Constants.ZERO_FLOAT, startMargin = DimenDp.SmallPadding, endMargin = DimenDp.ExtraSmallPadding)
                    width= Dimension.fillToConstraints
                },
                text = buttonText,
                textAlign = TextAlign.Start,
                color = textColor
            )
        }
    }
}

@Composable
fun ImageCircleButton(
    modifier: Modifier= Modifier.size(smallIconCardSize()),
    content: ImageButtonCard,
    cardColor:Color=TodoListAppColors.secondaryColor,
    isClickable: Boolean=false,
    onClickAction: () -> Unit
){
    Box(
        modifier
            .padding(DimenDp.IconPadding)
            .background(
                if (isClickable) cardColor else cardColor.copy(alpha = ZERO_FIVE_FLOAT),
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickableWithoutRipple { onClickAction() },
        contentAlignment = Alignment.Center,
    ) {
        content.apply {
            when(this){
                is ImageButtonCard.IconButton->{
                    Icon(
                        modifier = Modifier.wrapContentSize(),
                        painter =  painterResource(id = resource),
                        contentDescription = Constants.EMPTY_STRING,
                        tint = tint?:MaterialTheme.colorScheme.onSurface,
                    )
                }
                is ImageButtonCard.ImageButton->{
                    Image(painter = painterResource(id = resource), contentDescription =Constants.EMPTY_STRING,
                        modifier= Modifier
                            .wrapContentSize())
                }
                else->Unit
            }
        }
    }
}

@Composable
fun RoundShapeIconButton(
    modifier: Modifier= Modifier,
    @DrawableRes icon: Int,
    iconColor: Color,
    cardColor: Color,
    iconSize: Dp= smallIconSize(),
    onClickAction: (() -> Unit)?=null,
){
    val cardModifier = modifier
        .background(cardColor, shape = iconCardShape())
        .clip(iconCardShape())

    Box(
        cardModifier.then(if (onClickAction!=null) cardModifier.clickableWithoutRipple { onClickAction() } else cardModifier),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter =  painterResource(id = icon),
            contentDescription = Constants.EMPTY_STRING,
            tint = iconColor,
        )
    }
}

@Composable
fun ToggleIconButton(
    modifier: Modifier= Modifier.size(iconRoundButtonSize()).padding(DimenDp.IconPadding),
    @DrawableRes icon: Int,
    isChecked:Boolean,
    uncheckedCardColor:Color = Color.White,
    onClickAction: (() -> Unit)?=null,
){
    val iconColor = if (isChecked) TodoListAppColors.whiteColor else TodoListAppColors.selectedIconColor
    val cardColor = if (isChecked) TodoListAppColors.selectedIconColor else uncheckedCardColor

    RoundShapeIconButton(modifier.background(cardColor, shape = iconCardShape())
        .clip(iconCardShape()), icon, iconColor, cardColor){
        if (onClickAction != null) {
            onClickAction()
        }
    }
}

@Composable
fun ToggleIconButton(
    modifier: Modifier= Modifier.size(iconRoundButtonSize()).padding(DimenDp.IconPadding),
    @DrawableRes icon: Int,
    isChecked:Boolean,
    uncheckedCardColor:Color = Color.White,
){
    val iconColor = if (isChecked) TodoListAppColors.whiteColor else TodoListAppColors.selectedIconColor
    val cardColor = if (isChecked) TodoListAppColors.selectedIconColor else uncheckedCardColor

    RoundShapeIconButton(modifier.background(cardColor, shape = iconCardShape())
        .clip(iconCardShape()), icon, iconColor, cardColor)
}

@Composable
fun FloatingActionButton(
    modifier: Modifier,
    icon:Int,
    onClick:()->Unit
){
    Box(
        modifier = modifier
            .clickableWithoutRipple { onClick() }
            .size(floatingActionButtonSize())
            .background(color = cardBackground, shape = CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ){
        Box(
            modifier = Modifier
                .size(floatingActionInnerButtonSize())
                .background(brush = TodoListAppColors.backgroundGradient, shape = CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ){
            Icon(painter = painterResource(id = icon), contentDescription = Constants.EMPTY_STRING, tint = Color.White)
        }
    }
}

@Composable
fun SimpleBorderAndBackgroundLess_TextButton(
    buttonText:String,
    textColor: Color,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    modifier: Modifier,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: ()->Unit
){
    Row(modifier = modifier.clickableWithoutRipple { onClickAction() }) {
        if (leadingIcon!=null)
            leadingIcon()
        Text(text = buttonText,
            color = textColor,
            style = textStyle,
            textAlign = TextAlign.Center
        )
        if (trailingIcon != null)
            trailingIcon()
    }
}