package com.example.realworld.util

class ListFactory {
    companion object {
        fun <T> generate(factory: () -> T, min: Int = 1, max: Int = 5): List<T> {
            return (min..max).map { factory() }
        }
    }
}