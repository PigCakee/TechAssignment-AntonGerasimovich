package com.app.demo.navigation.api

import kotlinx.coroutines.flow.Flow

interface NavigationManager {

    val navigationCommandFlow: Flow<NavigationCommand>


    fun navigate(command: NavigationCommand)
}