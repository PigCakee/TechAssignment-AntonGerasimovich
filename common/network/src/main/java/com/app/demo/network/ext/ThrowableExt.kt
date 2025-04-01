package com.app.demo.network.ext

import com.app.demo.network.R
import com.app.demo.network.error.HttpException
import com.app.demo.ui.model.UiText

fun Throwable.toUiText(): UiText {
    return when (this) {
        is HttpException -> toUiText()
        else -> if (message != null) {
            UiText.Text(message!!)
        } else {
            UiText.Id(R.string.error_generic)
        }
    }
}