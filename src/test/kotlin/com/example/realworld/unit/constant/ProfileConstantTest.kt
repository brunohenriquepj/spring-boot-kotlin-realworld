package com.example.realworld.unit.constant

import com.example.realworld.constant.ProfileConstant
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ProfileConstantTest {
    @Test
    fun `Production should have right value`() {
        ProfileConstant.Production shouldBe "prod"
    }
}
