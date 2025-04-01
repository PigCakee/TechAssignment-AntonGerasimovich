package com.app.demo.navigation.api

import com.app.demo.ui.model.UiText

sealed interface NavigationCommand {
    data class Forward(
        val destination: Any
    ) : NavigationCommand

    data class ShowToast(
        val title: UiText,
    ) : NavigationCommand
}
