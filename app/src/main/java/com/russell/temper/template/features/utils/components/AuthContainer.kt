package com.russell.temper.template.features.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.russell.temper.template.R
import com.russell.temper.template.features.utils.Colors
import com.russell.temper.template.features.utils.Spacings
import com.russell.temper.template.features.utils.TemperTypography
import com.russell.temper.template.features.utils.TemperTypography.semiBold

class AuthContainerSpec {
    companion object {
        val buttonsCornerRadius = 8.dp
        val loginButtonBorderWidth = 1.dp
    }
}

@Composable
fun AuthContainer(
    modifier: Modifier = Modifier,
    onSignupButtonClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Colors.palette.systemBackgroundLevel0)
            .padding(Spacings.regular),
        horizontalArrangement = Arrangement.spacedBy(Spacings.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onSignupButtonClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Colors.palette.accent),
            shape = RoundedCornerShape(AuthContainerSpec.buttonsCornerRadius),
            elevation = null,
        ) {
            Text(
                text = stringResource(id = R.string.register_button_text),
                color = Colors.palette.primary,
                style = TemperTypography.bodyM.semiBold
            )
        }

        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = onLoginButtonClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            shape = RoundedCornerShape(AuthContainerSpec.buttonsCornerRadius),
            border = BorderStroke(
                width = AuthContainerSpec.loginButtonBorderWidth,
                color = Colors.palette.primary
            ),
        ) {
            Text(
                text = stringResource(id = R.string.login_button_text),
                color = Colors.palette.primary,
                style = TemperTypography.bodyM.semiBold
            )
        }
    }
}

@Preview
@Composable
fun AuthContainerPreview() {
    AuthContainer(onSignupButtonClicked = { }) {}
}
