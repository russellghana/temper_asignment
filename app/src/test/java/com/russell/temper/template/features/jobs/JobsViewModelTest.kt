package com.russell.temper.template.features.jobs

import com.russell.temper.template.BaseUnitTest
import com.russell.temper.template.core.Failure
import com.russell.temper.template.core.TemperResult
import com.russell.temper.template.core.getLongDisplayName
import com.russell.temper.template.data.repository.jobs.JobsApi
import com.russell.temper.template.data.repository.jobs.JobsNetworkMapper
import com.russell.temper.template.data.repository.jobs.JobsRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class JobsViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: JobsViewModel
    private lateinit var jobsRepository: JobsRepositoryImpl

    @MockK
    lateinit var jobsApi: JobsApi

    @MockK
    lateinit var jobsNetworkMapper: JobsNetworkMapper

    @Before
    override fun setUp() {
        super.setUp()
        jobsRepository = JobsRepositoryImpl(jobsApi, jobsNetworkMapper)
        viewModel = JobsViewModel(jobsRepository)
    }

    @After
    override fun tearDown() {
        viewModel.apply {
            isLoadingData.value = true
            failureReason.value = null
            jobs.value = null
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getJobs should ask repository for fetching data`() = runTest {
        // Given
        coEvery { jobsRepository.getJobs(any()) } returns mockk()

        // When
        viewModel.getJobs()

        // Then
        coVerify(exactly = 1) { jobsRepository.getJobs(any()) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getJobs should initiate jobs list in case of success`() = runTest {
        // Given
        coEvery { jobsRepository.getJobs(any()) } returns TemperResult.Success(emptyList())

        // When
        viewModel.getJobs()

        // Then
        viewModel.jobs `should not be` null
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getJobs should initiate failure reason  in case of failure`() = runTest {
        // Given
        coEvery { jobsRepository.getJobs(any()) } returns TemperResult.Error(Failure.Unknown)

        // When
        viewModel.getJobs()

        // Then
        viewModel.jobs.value `should be` null
        viewModel.failureReason `should not be` null
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getJobs should reset loading state after fetching response`() = runTest {
        // Given
        coEvery { jobsRepository.getJobs(any()) } returns mockk()

        // When
        viewModel.getJobs()

        // Then
        viewModel.isLoadingData.value `should be` false
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getCurrentFilterDateString should return today date's long display name`() = runTest {

        val todayDate = Date()
        val expectedDateDisplayName = todayDate.getLongDisplayName()

        // When
        val actualDateDisplayName = viewModel.getCurrentFilterDateString()

        // Then
        actualDateDisplayName shouldBeEqualTo expectedDateDisplayName
    }
}
