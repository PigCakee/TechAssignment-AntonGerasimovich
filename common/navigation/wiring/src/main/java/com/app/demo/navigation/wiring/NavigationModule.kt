package com.app.demo.navigation.wiring

import com.app.demo.navigation.api.NavigationManager
import com.app.demo.navigation.impl.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    @Singleton
    fun bindNavigationManager(navigationManagerImpl: NavigationManagerImpl): NavigationManager
}