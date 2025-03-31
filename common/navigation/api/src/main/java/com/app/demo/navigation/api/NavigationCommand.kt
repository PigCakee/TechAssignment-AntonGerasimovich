package com.app.demo.navigation.api

import com.app.demo.ui.model.UiText

sealed interface NavigationCommand {

    data object Back : NavigationCommand

    data class Forward(
        val destination: Any,
        val singleTop: Boolean = false,
    ) : NavigationCommand

    data class ShowToast(
        val title: UiText,
    ) : NavigationCommand
}
