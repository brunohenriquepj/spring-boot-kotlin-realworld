package com.example.realworld.entity

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
class User(email: String, userName: String, bio: String? = null, image: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    var email: String = email
        private set

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    var userName: String = userName
        private set

    var bio: String? = bio
        private set

    var image: String? = image
        private set
}