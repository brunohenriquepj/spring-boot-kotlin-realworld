package com.example.realworld.entity

import javax.persistence.*

@Entity
class User(email: String, userName: String, bio: String? = null, image: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false, unique = true)
    var email: String = email
        private set

    @Column(nullable = false, unique = true)
    var userName: String = userName
        private set

    var bio: String? = bio
        private set

    var image: String? = image
        private set
}