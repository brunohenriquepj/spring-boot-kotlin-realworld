package com.example.realworld.util

import com.github.javafaker.Faker
import java.util.*

class FakerPtBr {
    companion object {
        fun generate(): Faker {
            return Faker(Locale("pt", "BR"))
        }
    }
}