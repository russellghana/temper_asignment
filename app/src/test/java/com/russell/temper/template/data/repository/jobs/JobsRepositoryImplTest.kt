package com.russell.temper.template.data.repository.jobs

import com.russell.temper.template.BaseUnitTest
import com.russell.temper.template.core.TemperResult
import com.russell.temper.template.domain.model.TemperJob
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class JobsRepositoryImplTest : BaseUnitTest() {

    @MockK
    lateinit var jobsApi: JobsApi

    @MockK
    lateinit var jobsNetworkMapper: JobsNetworkMapper

    @ExperimentalCoroutinesApi
    @Test
    fun `when getting jobs list is successful it should return Success result`() = runTest {
        // Given
        val testDate = "2022-06-07"
        val jobsRepositoryImpl = JobsRepositoryImpl(jobsApi, jobsNetworkMapper)
        val mockedTestList: List<TemperJob> = mockk()
        val expectedSuccessResult = TemperResult.Success(mockedTestList)

        coEvery { jobsApi.getJobs(any()) } returns mockk()
        coEvery { jobsNetworkMapper.mapFromEntity(any()) } returns mockedTestList

        // When
        val result = jobsRepositoryImpl.getJobs(testDate)

        // Then
        result shouldBeEqualTo expectedSuccessResult
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getting jobs list throws an exception it should return error result`() = runTest {
        // Given
        val testDate = "2022-06-07"
        val jobsRepositoryImpl = JobsRepositoryImpl(jobsApi, jobsNetworkMapper)
        val testException = Exception()
        val expectedResult = TemperResult.Error(testException)
        coEvery { jobsApi.getJobs(any()) } throws testException

        // When
        val result = jobsRepositoryImpl.getJobs(testDate)

        // Then
        result shouldBeEqualTo expectedResult
    }
}
