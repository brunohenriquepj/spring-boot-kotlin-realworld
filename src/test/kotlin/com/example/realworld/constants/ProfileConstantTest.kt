package com.example.realworld.constants

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ProfileConstantTest {
    @Test
    fun `Production should have right value`() {
        ProfileConstant.Production shouldBe "prod"
    }
}
