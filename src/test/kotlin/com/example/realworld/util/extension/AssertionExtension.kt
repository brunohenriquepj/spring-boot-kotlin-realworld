package com.example.realworld.util.extension

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.assertions.assertionCounter
import io.kotest.assertions.collectOrThrow
import io.kotest.assertions.eq.eq
import io.kotest.assertions.errorCollector

infix fun <T> String.shouldBeEqualJson(expected: T) {
    val actual = this
    assertionCounter.inc()
    val expectedJson = ObjectMapper().writeValueAsString(expected)
    eq(actual, expectedJson)?.let(errorCollector::collectOrThrow)
}
