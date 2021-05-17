package com.example.realworld.util.builder.common

import com.example.realworld.util.FakerPtBr

class StringBuilder {
    private val instance: String

    init {
        val faker = FakerPtBr.generate()
        instance = faker.lorem().sentence()
    }

    fun build(): String {
        return instance
    }
}