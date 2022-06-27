package com.russell.temper.template.data.repository.jobs

import com.russell.temper.template.data.model.JobCategory
import com.russell.temper.template.data.model.JobDetail
import com.russell.temper.template.data.model.JobEarning
import com.russell.temper.template.data.model.JobInformationNetworkEntity
import com.russell.temper.template.data.model.JobLinks
import com.russell.temper.template.data.model.JobsNetworkEntity
import com.russell.temper.template.domain.model.TemperJob
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class JobsNetworkMapperTest {

    @Test
    fun `map to job network entity to list of TemperJob object`() {
        // Given
        val jobsNetworkMapper = JobsNetworkMapper()

        val jobsNetworkEntity = JobsNetworkEntity(
            data = listOf(
                JobInformationNetworkEntity(
                    id = "1",
                    JobDetail(
                        id = "2",
                        title = "job title",
                        category = JobCategory(
                            id = "3",
                            name = "Job Category"
                        ),
                        links = JobLinks(null)
                    ),
                    JobEarning("EUR", 10.0f),
                    starts_at = "2022-06-27T17:00:00+02:00",
                    ends_at = "2022-06-27T23:00:00+02:00"
                )
            )
        )

        val expectedListOfJobs: List<TemperJob> = listOf(
            TemperJob(
                id = "1",
                title = "job title",
                earning = "10.0 €",
                imageUrl = null,
                category = "Job Category",
                workingHours = "17:00-23:00"
            )
        )

        // When
        val mappedListOfJobs = jobsNetworkMapper.mapFromEntity(jobsNetworkEntity)

        // Then
        mappedListOfJobs shouldBeEqualTo expectedListOfJobs
    }

    @Test
    fun `parse working hours should create formatted time string`() {
        // Given
        val jobsNetworkMapper = JobsNetworkMapper()
        val testStartDate = "2022-06-27T17:00:00+02:00"
        val testEndDate = "2022-06-27T23:00:00+02:00"
        val expectedTimeString = "17:00-23:00"

        // When
        val formattedTimeString = jobsNetworkMapper.parseWorkingHours(testStartDate, testEndDate)

        // Then
        formattedTimeString shouldBeEqualTo expectedTimeString
    }

    @Test
    fun `parse earning should return the amount with unknown currency sign (¤) if its unsupported`() {
        // Given
        val jobsNetworkMapper = JobsNetworkMapper()
        val testJobEarning = JobEarning("%%", 15f)
        val expectedEarning = "15.0 ¤"

        // When
        val parseEarningResult = jobsNetworkMapper.parseEarnings(testJobEarning)

        // Then
        parseEarningResult shouldBeEqualTo expectedEarning
    }

    @Test
    fun `parse earning should return the amount with corresponding currency sign`() {
        // Given
        val jobsNetworkMapper = JobsNetworkMapper()
        val testJobEarning = JobEarning("EUR", 15f)
        val expectedEarning = "15.0 €"

        // When
        val parseEarningResult = jobsNetworkMapper.parseEarnings(testJobEarning)

        // Then
        parseEarningResult shouldBeEqualTo expectedEarning
    }
}
