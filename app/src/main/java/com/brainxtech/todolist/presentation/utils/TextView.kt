package com.brainxtech.todolist.presentation.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.todolist.presentation.theme.appFonts
import com.brainxtech.extensions.extension.buildStrikethrough
import com.brainxtech.extensions.utils.Constants
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ONE
import com.brainxtech.extensions.utils.Constants.ZERO


@Composable
fun GradientText(
    modifier: Modifier,
    text:String,
    colors:List<Color>,
    textSize: TextUnit,
    fontWeight: FontWeight,
    textAlign: TextAlign= TextAlign.Center,
    maxLines: Int=Int.MAX_VALUE
){

    Text(
        modifier =modifier,
        text = text,
        style = TextStyle(
            brush = Brush.linearGradient(colors),
            fontFamily = appFonts,
            fontWeight = fontWeight,
            fontSize = textSize
        ),
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BodyLargeText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun DisplayMediumText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.displayMedium,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun HeadlineMediumText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}
@Composable
fun HeadlineSmallText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun DisplaySmallText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.displaySmall,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun BodySmallText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        maxLines=maxLines,
        overflow = TextOverflow.Ellipsis

    )
}
@Composable
fun LabelSmallText(
    modifier: Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines:Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun CharacterCountAnimatedText(text: String,color: Color=TodoListAppColors.menuSelectedTextColor){
    AnimatedContent(targetState = text, transitionSpec = {
        (slideInVertically() + fadeIn()).togetherWith(slideOutVertically() + fadeOut())
    }, label = Constants.EMPTY_STRING) {
        LabelSmallText(
            modifier = Modifier.wrapContentSize(),
            text = it ,
            color = color)
    }
}

@Composable
fun StrikethroughText(
    modifier: Modifier,
    text:String,
    textStyle: TextStyle,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines:Int=Int.MAX_VALUE
){
    Text(
        modifier =modifier,
        text = text,
        color = color,
        style = textStyle.copy(textDecoration = TextDecoration.LineThrough),
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}


@Composable
fun AnimatedStrikethroughText(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    animateOnHide: Boolean = true,
    spec: AnimationSpec<Int> = tween(text.length * 30, easing = FastOutLinearInEasing),
    strikethroughStyle: SpanStyle = SpanStyle(),
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines:Int=Int.MAX_VALUE
) {
    var textToDisplay by remember { mutableStateOf(AnnotatedString(EMPTY_STRING)) }

    val length = remember { Animatable(initialValue = ZERO, typeConverter = Int.VectorConverter) }

    LaunchedEffect(length.value) {
        textToDisplay = text.buildStrikethrough(length.value, strikethroughStyle)
    }

    LaunchedEffect(isVisible) {
        when {
            isVisible -> length.animateTo(text.length, spec)
            !isVisible && animateOnHide -> length.animateTo(ZERO, spec)
            else -> length.snapTo(ZERO)
        }
    }

    LaunchedEffect(text) {
        when {
            isVisible && text.length == length.value -> {
                textToDisplay = text.buildStrikethrough(length.value, strikethroughStyle)
            }
            isVisible && text.length != length.value -> {
                length.snapTo(text.length)
            }
            else -> textToDisplay = AnnotatedString(text)
        }
    }

    Text(
        text = textToDisplay,
        modifier = modifier,
        style = textStyle,
        textAlign = textAlign,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun LeadingIconWithText(icon:Int,text:Int){
    Row(Modifier.wrapContentSize().padding(DimenDp.SmallPadding), horizontalArrangement = Arrangement.spacedBy(DimenDp.ExtraSmallMargin), verticalAlignment = Alignment.CenterVertically) {
        Icon(modifier = Modifier.size(smallIconSize()),painter = painterResource(id = icon), contentDescription = EMPTY_STRING, tint = TodoListAppColors.greyIconColor)
        BodySmallText(modifier = Modifier.wrapContentSize(), text = stringResource(id = text), textAlign = TextAlign.Start, color = TodoListAppColors.greyTextColor4, maxLines = ONE)
    }
}
