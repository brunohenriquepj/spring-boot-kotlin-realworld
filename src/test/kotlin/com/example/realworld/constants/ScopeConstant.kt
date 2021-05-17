package com.example.realworld.constants

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScopeConstantTest {
    @Test
    fun `Request should have right value`() {
        ScopeConstant.Request shouldBe "request"
    }
}
