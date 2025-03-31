package com.app.demo.navigation.api

import com.app.demo.ui.model.UiText

fun NavigationManager.forwardTo(destination: Any) = navigate(NavigationCommand.Forward(destination))

fun NavigationManager.back() = navigate(NavigationCommand.Back)

fun NavigationManager.showToast(
     title: UiText,
) = navigate(
    NavigationCommand.ShowToast(
        title = title,
    )
)

