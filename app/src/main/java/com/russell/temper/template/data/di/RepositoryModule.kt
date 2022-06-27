package com.russell.temper.template.data.di

import com.russell.temper.template.data.repository.jobs.JobsRepository
import com.russell.temper.template.data.repository.jobs.JobsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindJobsRepository(repository: JobsRepositoryImpl): JobsRepository
}
