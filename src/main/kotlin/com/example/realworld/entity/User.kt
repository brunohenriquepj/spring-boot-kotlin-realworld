package com.example.realworld.entity

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class User(email: String, userName: String, password: String, bio: String? = null, image: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @NotBlank
    @Column(nullable = false, unique = true)
    var email: String = email
        private set

    @NotBlank
    @Column(nullable = false, unique = true)
    var userName: String = userName
        private set

    @NotBlank
    @Length(min = 8, max = 100)
    var password: String = password
        private set

    var bio: String? = bio
        private set

    var image: String? = image
        private set
}