package com.russell.temper.template.features.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.russell.temper.template.R
import com.russell.temper.template.domain.model.TemperJob
import com.russell.temper.template.features.utils.Colors
import com.russell.temper.template.features.utils.Colors.palette
import com.russell.temper.template.features.utils.Spacings
import com.russell.temper.template.features.utils.TemperTypography
import com.russell.temper.template.features.utils.TemperTypography.semiBold
import com.russell.temper.template.features.utils.components.AuthContainer
import com.russell.temper.template.features.utils.components.CustomAppBar

@Composable
fun JobsScreen(
    viewModel: JobsViewModel = hiltViewModel(),
    onAuthButtonClicked: () -> Unit,
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getJobs()
    }

    val isLoadingData by viewModel.isLoadingData
    val jobs by viewModel.jobs
    val currentDate by viewModel.currentDate
    val failure by viewModel.failureReason

    when {
        failure != null -> JobScreenErrorView()
        else -> JobsScreenView(
            isLoadingData = isLoadingData,
            currentDate = currentDate,
            jobs = jobs,
            onSignupButtonClicked = onAuthButtonClicked,
            onLoginButtonClicked = onAuthButtonClicked
        )
    }
}

@Composable
fun JobsScreenView(
    isLoadingData: Boolean,
    currentDate: String,
    jobs: List<TemperJob>?,
    onSignupButtonClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit
) {

    Scaffold(
        backgroundColor = palette.systemBackgroundLevel0,
        topBar = {
            CustomAppBar(title = currentDate)
        }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .background(color = Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Colors.palette.systemBackgroundLevel0),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (isLoadingData) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    } else {
                        jobs?.let {
                            LazyColumn() {
                                items(jobs) { jobItem ->
                                    JobItemCard(job = jobItem) { }
                                }
                            }
                        }
                    }

                    FilterButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = Spacings.regular)
                    )
                }

                AuthContainer(
                    onSignupButtonClicked = onSignupButtonClicked,
                    onLoginButtonClicked = onLoginButtonClicked
                )
            }
        }
    }
}

@Composable
private fun FilterButton(modifier: Modifier) {
    Button(
        modifier = modifier,
        onClick = { /* onFilterButtonClicked */ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Colors.palette.systemBackgroundLevel0),
        shape = RoundedCornerShape(Spacings.increased),
        contentPadding = PaddingValues(horizontal = Spacings.regular)
    ) {
        Icon(
            modifier = Modifier.padding(Spacings.tiny),
            painter = painterResource(id = R.drawable.ic_filter),
            tint = palette.primary,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(Spacings.small),
            text = stringResource(id = R.string.filter_button_text),
            color = palette.primary,
            style = TemperTypography.bodyS.semiBold
        )
    }
}

@Composable
fun JobScreenErrorView() {
    // custom error view in here
}
