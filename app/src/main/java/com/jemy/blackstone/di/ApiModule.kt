package com.jemy.blackstone.di

import com.jemy.blackstone.data.remote.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit): Api = retrofit.create(
        Api::class.java
    )
}