package com.jemy.blackstone.di

import com.jemy.blackstone.data.repository.LatestRepository
import com.jemy.blackstone.ui.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun getMainModelFactory(
        repository: LatestRepository
    ): MainViewModelFactory = MainViewModelFactory(repository)
}