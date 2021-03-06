package com.russell.temper.template.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideHandler(): Handler = Handler(Looper.getMainLooper())

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context
}
