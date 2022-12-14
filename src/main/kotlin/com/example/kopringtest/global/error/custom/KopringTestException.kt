package com.example.kopringtest.global.error.custom

import com.example.kopringtest.global.error.property.ErrorProperty

open class KopringTestException(
    private val errorProperty: ErrorProperty
) : RuntimeException() {

    val status: Int
        get() = errorProperty.status

    val errorMessage: String
        get() = errorProperty.errorMessage
}