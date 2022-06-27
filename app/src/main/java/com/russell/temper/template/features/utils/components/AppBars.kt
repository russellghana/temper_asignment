package com.russell.temper.template.features.utils.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.russell.temper.template.features.utils.Colors.palette
import com.russell.temper.template.features.utils.Spacings
import com.russell.temper.template.features.utils.TemperTypography

@Composable
fun CustomAppBar(
    title: String,
    onClickBack: (() -> Unit)? = null,
) {
    ProvideWindowInsets {
        TopAppBar(
            modifier = Modifier
                .statusBarsPadding(),
            title = {
                Text(
                    text = title,
                    color = palette.primary,
                    style = TemperTypography.h3
                )
            },
            elevation = Spacings.zero,
            backgroundColor = Color(0xD9FFFFFF).compositeOver(Color.White),
            navigationIcon = onClickBack?.let {
                {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                            contentDescription = null,
                            tint = palette.primary
                        )
                    }
                }
            }
        )
    }
}
