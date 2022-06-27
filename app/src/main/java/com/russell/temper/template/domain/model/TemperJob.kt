package com.russell.temper.template.domain.model

data class TemperJob(
    val id: String,
    val title: String,
    val earning: String,
    val imageUrl: String?,
    val category: String,
    val workingHours: String
)
