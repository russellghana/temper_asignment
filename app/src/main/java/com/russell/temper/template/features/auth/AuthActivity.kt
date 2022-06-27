package com.russell.temper.template.features.auth

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.russell.temper.template.R
import com.russell.temper.template.features.utils.Colors
import com.russell.temper.template.features.utils.components.CustomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val context = LocalContext.current

            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = true)
            }

            Scaffold(
                backgroundColor = Colors.palette.systemBackgroundLevel0,
                topBar = {
                    CustomAppBar(
                        title = stringResource(id = R.string.auth_screen_title),
                        onClickBack = {
                            (context as Activity).finish()
                        }
                    )
                }
            ) { innerPadding ->
                Box(
                    Modifier
                        .padding(innerPadding)
                        .background(color = Color.Transparent)
                ) {
                }
            }
        }
    }
}
