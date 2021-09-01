package com.example.realworld.dto.user.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateUserRequest(@field:NotNull @field:Valid val user: UpdateUserRequestData? = null)

class UpdateUserRequestData(
    email: String = "",
    password: String = "",
    userName: String = "",
    bio: String? = null,
    image: String? = null,
) {
    @field:NotBlank
    @field:Length(max = 255)
    var userName: String = userName
        private set

    @field:NotBlank
    @field:Length(max = 255)
    var email: String = email
        private set

    @field:NotBlank
    @field:Length(min = 8, max = 100)
    var password: String = password
        private set

    @field:Length(max = 250)
    var bio: String? = bio
        private set

    @field:URL
    var image: String? = image
        private set
}
