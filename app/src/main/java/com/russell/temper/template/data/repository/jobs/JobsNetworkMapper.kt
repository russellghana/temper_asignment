package com.russell.temper.template.data.repository.jobs

import com.russell.temper.template.core.createFromServerDateTime
import com.russell.temper.template.core.toTimeOfDayString
import com.russell.temper.template.data.utils.EntityMapper
import com.russell.temper.template.data.model.JobEarning
import com.russell.temper.template.data.model.JobsNetworkEntity
import com.russell.temper.template.domain.model.TemperJob
import java.util.*
import javax.inject.Inject

class JobsNetworkMapper @Inject constructor() :
    EntityMapper<JobsNetworkEntity, List<TemperJob>> {
    override fun mapFromEntity(entity: JobsNetworkEntity): List<TemperJob> {
        return entity.data.map { jobInfo ->
            TemperJob(
                id = jobInfo.id,
                title = jobInfo.job.title,
                earning = parseEarnings(jobInfo.earnings_per_hour),
                imageUrl = jobInfo.job.links.hero_380_image,
                category = jobInfo.job.category.name,
                workingHours = parseWorkingHours(jobInfo.starts_at, jobInfo.ends_at)
            )
        }
    }

    fun parseWorkingHours(startDateString: String, endDateString: String): String {
        val startDate = Date().createFromServerDateTime(startDateString)
        val endDate = Date().createFromServerDateTime(endDateString)
        return "${startDate.toTimeOfDayString()}-${endDate.toTimeOfDayString()}"
    }

    fun parseEarnings(jobEarning: JobEarning): String {
        val currencySign = when (jobEarning.currency) {
            "EUR" -> "€"
            else -> "¤" // unknown currency sign
        }
        return "${jobEarning.amount} $currencySign"
    }
}
