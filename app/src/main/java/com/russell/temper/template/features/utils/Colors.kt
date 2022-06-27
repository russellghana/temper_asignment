package com.russell.temper.template.features.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {

    val palette: Palette
        @Composable
        get() = if (isSystemInDarkTheme()) Palette.Dark else Palette.Light

    sealed class Palette {
        abstract val systemBackgroundLevel0: Color

        abstract val primary: Color
        abstract val inverted: Color
        abstract val accent: Color
        abstract val purple: Color

        object Light : Palette() {
            override val systemBackgroundLevel0 = Neutral0

            override val primary = Neutral900
            override val inverted = Neutral0
            override val accent = MainGreen500
            override val purple = MainPurple400
        }

        object Dark : Palette() {
            override val systemBackgroundLevel0 = Neutral1000

            override val primary = Neutral100
            override val inverted = Neutral1000
            override val accent = MainGreen500
            override val purple = MainPurple400
        }
    }

    private val Neutral0 = Color(0xFFFFFFFF)
    private val Neutral100 = Color(0xFFEFEFF3)
    private val Neutral900 = Color(0xFF2E2F3C)
    private val Neutral1000 = Color(0xFF121217)

    private val MainGreen500 = Color(0xFF8BC34A)
    private val MainPurple400 = Color(0xFF9C27B0)
}
