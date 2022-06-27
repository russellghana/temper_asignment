package com.russell.temper.template.data.repository.jobs

import com.russell.temper.template.data.model.JobsNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {
    @GET("api/v3/shifts?")
    suspend fun getJobs(@Query("filter[date]") date: String): JobsNetworkEntity
}
