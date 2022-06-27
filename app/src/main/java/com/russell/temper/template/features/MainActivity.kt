package com.russell.temper.template.features

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.russell.temper.template.features.auth.AuthActivity
import com.russell.temper.template.features.jobs.JobsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val context = LocalContext.current

            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = true)
            }

            JobsScreen(
                onAuthButtonClicked = {
                    // should handled bt navigation system, skipping for now since the navigation
                    // system is not in place
                    // we can a new activity or navigate through another screen composable
                    context.startActivity(Intent(context, AuthActivity::class.java))
                }
            )
        }
    }
}
