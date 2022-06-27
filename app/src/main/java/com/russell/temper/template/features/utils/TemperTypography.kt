package com.russell.temper.template.features.utils

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.russell.temper.template.R
import com.russell.temper.template.features.utils.TemperTypography.FontFamilies.roboto

object TemperTypography {

    object FontFamilies {
        val roboto = FontFamily(
            Font(R.font.roboto_regular, weight = FontWeight.Normal),
            Font(R.font.roboto_bold, weight = FontWeight.Bold)
        )
    }

    val TextStyle.semiBold: TextStyle
        get() = this.copy(fontWeight = FontWeight.SemiBold)

    val TextStyle.bold: TextStyle
        get() = this.copy(fontWeight = FontWeight.Bold)

    val h3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontFamily = roboto
    )
    val bodyM = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = roboto
    )
    val bodyS = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        fontFamily = roboto
    )
}
