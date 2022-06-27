package com.russell.temper.template.features.jobs

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russell.temper.template.core.Failure
import com.russell.temper.template.core.getLongDisplayName
import com.russell.temper.template.core.onFailure
import com.russell.temper.template.core.onSuccess
import com.russell.temper.template.core.toServerStringFormat
import com.russell.temper.template.data.repository.jobs.JobsRepository
import com.russell.temper.template.domain.model.TemperJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : ViewModel() {

    private val filterDate = mutableStateOf(Date())
    val isLoadingData = mutableStateOf(true)
    val failureReason = mutableStateOf<Failure?>(null)
    val jobs = mutableStateOf<List<TemperJob>?>(null)
    val currentDate = mutableStateOf(getCurrentFilterDateString())

    fun getJobs() = viewModelScope.launch {
        isLoadingData.value = true
        val jobsResult = jobsRepository.getJobs(filterDate.value.toServerStringFormat())
        jobsResult.onSuccess { jobsList ->
            isLoadingData.value = false
            jobs.value = jobsList
        }.onFailure {
            isLoadingData.value = false
            failureReason.value = it
            jobs.value = null
        }
    }

    fun getCurrentFilterDateString(): String = filterDate.value.getLongDisplayName()
}
