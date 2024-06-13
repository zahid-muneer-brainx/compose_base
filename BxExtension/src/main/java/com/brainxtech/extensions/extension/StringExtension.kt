package com.brainxtech.extensions.extension

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

fun String.buildStrikethrough(length: Int, style: SpanStyle) = buildAnnotatedString {
    append(this@buildStrikethrough)
    val strikethroughStyle = style.copy(textDecoration = TextDecoration.LineThrough)
    addStyle(strikethroughStyle, 0, length)
}