package com.example.realworld.util.builder

import com.example.realworld.util.builder.common.StringBuilder
import org.springframework.validation.FieldError

class FieldErrorBuilder {
    private val instance: FieldError = FieldError(
        StringBuilder().build(),
        StringBuilder().build(),
        StringBuilder().build()
    )

    fun build(): FieldError {
        return instance
    }
}