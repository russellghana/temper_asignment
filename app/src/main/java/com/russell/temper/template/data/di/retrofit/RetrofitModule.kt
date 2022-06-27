package com.russell.temper.template.data.di.retrofit

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.russell.temper.template.BuildConfig
import com.russell.temper.template.data.repository.jobs.JobsApi
import com.russell.temper.template.data.retrofit.BasicRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val NETWORK_TIMEOUT: Long = 30

    @Provides
    @Singleton
    fun provideJackson(): ObjectMapper {
        return jacksonObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        )
    }

    @Provides
    @Singleton
    @InterceptorQualifiers.BasicRequestInterceptor
    fun provideBasicClientBuilder(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder().retryOnConnectionFailure(true)
            .callTimeout(0, TimeUnit.SECONDS).connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(BasicRequestInterceptor())
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
        }
        return builder
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        jacksonMapper: ObjectMapper
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
    }

    @Provides
    @Singleton
    fun provideJobsApiService(
        @InterceptorQualifiers.BasicRequestInterceptor okHttpClient: OkHttpClient.Builder,
        retrofit: Retrofit.Builder,
    ): JobsApi {
        return retrofit.client(okHttpClient.build())
            .build()
            .create(JobsApi::class.java)
    }
}
