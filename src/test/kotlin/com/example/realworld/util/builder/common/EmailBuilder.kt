package com.example.realworld.util.builder.common

import com.example.realworld.util.FakerPtBr

class EmailBuilder {
    private var instance: String

    init {
        val faker = FakerPtBr.generate()
        instance = faker.internet().emailAddress()
    }

    fun build(): String {
        return instance
    }
}