package com.russell.temper.template.data.repository.jobs

import com.russell.temper.template.core.TemperResult
import com.russell.temper.template.domain.model.TemperJob
import javax.inject.Inject

interface JobsRepository {
    suspend fun getJobs(date: String): TemperResult<List<TemperJob>>
}

class JobsRepositoryImpl @Inject constructor(
    private val jobsApi: JobsApi,
    private val jobsNetworkMapper: JobsNetworkMapper
) : JobsRepository {

    override suspend fun getJobs(date: String): TemperResult<List<TemperJob>> {
        return try {
            val networkResponse = jobsApi.getJobs(date)
            val jobsList = jobsNetworkMapper.mapFromEntity(networkResponse)
            TemperResult.Success(jobsList)
        } catch (e: Exception) {
            TemperResult.Error(e)
        }
    }
}

