package com.brainxtech.todolist.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.brainxtech.todolist.R

val appFonts = FontFamily(
    Font(R.font.inter_tight_regular),
    Font(R.font.inter_tight_bold),
    Font(R.font.inter_tight_light),
    Font(R.font.inter_tight_medium),
    Font(R.font.inter_tight_semi_bold),
    Font(R.font.inter_tight_extra_bold),
    Font(R.font.inter_tight_thin),
    Font(R.font.ubuntu_bold),
    Font(R.font.ubuntu_bold_italic),
    Font(R.font.ubuntu_italic),
    Font(R.font.ubuntu_light),
    Font(R.font.ubuntu_light_italic),
    Font(R.font.ubuntu_medium),
    Font(R.font.ubuntu_medium),
    Font(R.font.ubuntu_medium_italic),
    Font(R.font.ubuntu_regular),
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.W600,
        fontFamily = appFonts,
        fontSize = 38.sp,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.W600,
        fontFamily = appFonts,
        fontSize = 26.sp,
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.W600,
        fontFamily = appFonts,
        fontSize = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = appFonts,
        fontSize = 18.sp,
    ),
    bodySmall =  TextStyle(
        fontWeight = FontWeight.W500,
        fontFamily = appFonts,
        fontSize = 14.sp,
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = appFonts,
        fontSize = 18.sp,
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontFamily = appFonts,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = appFonts,
        fontSize = 12.sp,
    )
)