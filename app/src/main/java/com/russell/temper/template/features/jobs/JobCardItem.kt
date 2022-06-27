package com.russell.temper.template.features.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.russell.temper.template.R
import com.russell.temper.template.domain.model.TemperJob
import com.russell.temper.template.features.utils.Colors
import com.russell.temper.template.features.utils.Spacings
import com.russell.temper.template.features.utils.TemperTypography
import com.russell.temper.template.features.utils.TemperTypography.bold
import com.russell.temper.template.features.utils.TemperTypography.semiBold

private class JobItemCardSpec {
    companion object {
        val imageHeight = 220.dp
    }
}

@Composable
fun JobItemCard(job: TemperJob, onJobClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacings.small)
            .clickable { onJobClicked.invoke() },
        verticalArrangement = Arrangement.spacedBy(Spacings.tiny)
    ) {
        Box(
            modifier = Modifier
                .requiredHeight(JobItemCardSpec.imageHeight)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier.requiredHeight(JobItemCardSpec.imageHeight),
                model = job.imageUrl,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.item_placeholder),
                error = painterResource(id = R.drawable.item_placeholder),
                contentDescription = null
            )

            PriceText(
                modifier = Modifier.align(Alignment.BottomEnd),
                price = job.earning
            )
        }

        Text(
            text = job.category,
            style = TemperTypography.bodyS.semiBold,
            color = Colors.palette.purple
        )

        Text(
            text = job.title,
            style = TemperTypography.bodyM.bold,
            color = Colors.palette.primary
        )

        Text(
            text = job.workingHours,
            style = TemperTypography.bodyS,
            color = Colors.palette.primary
        )
    }
}

@Composable
private fun PriceText(
    modifier: Modifier = Modifier,
    price: String
) {
    Surface(
        modifier = modifier.background(color = Color.Transparent),
        shape = RoundedCornerShape(
            Spacings.regular,
            Spacings.zero,
            Spacings.zero,
            Spacings.zero,
        )
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = Spacings.small, vertical = Spacings.tiny)
                .background(color = Color.White),
            text = price,
            style = TemperTypography.bodyS.bold,
            color = Colors.palette.primary
        )
    }
}

@Preview
@Composable
fun JobItemCardPreview() {
    JobItemCard(
        job = TemperJob(
            id = "1",
            title = "Test Job",
            earning = "15 €",
            imageUrl = null,
            category = "Catering",
            workingHours = "11:30 - 15:30"
        )
    ) {
    }
}
