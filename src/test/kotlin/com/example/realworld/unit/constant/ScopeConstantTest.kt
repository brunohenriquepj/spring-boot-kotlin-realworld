package com.example.realworld.unit.constant

import com.example.realworld.constant.ScopeConstant
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScopeConstantTest {
    @Test
    fun `Request should have right value`() {
        ScopeConstant.Request shouldBe "request"
    }

    @Test
    fun `Prototype should have right value`() {
        ScopeConstant.Prototype shouldBe "prototype"
    }
}
