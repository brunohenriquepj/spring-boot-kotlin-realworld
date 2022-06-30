package com.example.realworld.util

import net.datafaker.Faker
import java.util.Locale

class FakerPtBr {
    companion object {
        fun generate(): Faker {
            return Faker(Locale("pt", "BR"))
        }
    }
}
