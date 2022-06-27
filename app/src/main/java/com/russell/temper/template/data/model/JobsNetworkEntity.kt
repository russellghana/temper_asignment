package com.russell.temper.template.data.model

data class JobsNetworkEntity(
    val data: List<JobInformationNetworkEntity>,
)

data class JobInformationNetworkEntity(
    val id: String,
    val job: JobDetail,
    val earnings_per_hour: JobEarning,
    val starts_at: String,
    val ends_at: String
)

data class JobDetail(
    val id: String,
    val title: String,
    val category: JobCategory,
    val links: JobLinks,
)

data class JobEarning(
    val currency: String,
    val amount: Float
)

data class JobCategory(
    val id: String,
    val name: String
)

data class JobLinks(
    val hero_380_image: String?
)
