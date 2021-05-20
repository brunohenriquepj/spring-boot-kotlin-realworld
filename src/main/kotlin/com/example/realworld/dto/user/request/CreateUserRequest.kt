package com.example.realworld.dto.user.request

import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateUserRequest(@field:NotNull @field:Valid val user: CreateUserRequestData? = null)

class CreateUserRequestData(
    email: String = "",
    password: String = "",
    userName: String = "",
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
    @field:Length(max = 100)
    var password: String = password
        private set
}
