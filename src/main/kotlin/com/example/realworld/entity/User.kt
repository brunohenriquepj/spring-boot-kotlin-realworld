package com.example.realworld.entity

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
class User(email: String, userName: String, password: String, bio: String? = null, image: String? = null, id: Long = 0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        private set

    @NotBlank
    @Email
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

    @Length(max = 250)
    var bio: String? = bio
        private set

    @URL
    var image: String? = image
        private set
}
