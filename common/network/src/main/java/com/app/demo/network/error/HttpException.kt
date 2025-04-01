package com.app.demo.network.error

import androidx.annotation.StringRes
import com.app.demo.network.R
import com.app.demo.ui.model.UiText

sealed class HttpException(val code: String, @StringRes private val stringId: Int) : Exception() {

    fun toUiText(): UiText {
        return UiText.Id(stringId)
    }

    companion object {
        fun transformed(code: String): HttpException {
            return when (code) {
                // Here you can add any custom error
                else -> GenericException()
            }
        }
    }
}

class GenericException : HttpException(CODE, R.string.error_generic) {
    companion object {
        const val CODE = ""
    }
}

