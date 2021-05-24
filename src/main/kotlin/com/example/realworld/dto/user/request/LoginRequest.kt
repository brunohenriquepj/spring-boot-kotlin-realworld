package com.example.realworld.dto.user.request

import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LoginRequest(@field:NotNull @field:Valid val user: LoginRequestData? = null)

class LoginRequestData(email: String = "", password: String = "") {
    @field:NotBlank
    @field:Email
    var email: String = email
        private set

    @field:NotBlank
    var password: String = password
        private set
}
