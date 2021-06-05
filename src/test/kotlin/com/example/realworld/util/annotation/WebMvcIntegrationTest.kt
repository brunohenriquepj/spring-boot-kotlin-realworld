package com.example.realworld.util.annotation

import com.example.realworld.controller.UserInfoController
import com.example.realworld.service.implementation.SecurityContextServiceImpl
import com.example.realworld.service.implementation.UserDetailsServiceImpl
import com.example.realworld.util.implementation.TokenUtilImpl
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.core.annotation.AliasFor
import kotlin.reflect.KClass

@WebMvcTest(controllers = [UserInfoController::class])
@Import(UserDetailsServiceImpl::class, TokenUtilImpl::class, SecurityContextServiceImpl::class)
annotation class WebMvcIntegrationTest(
    @get:AliasFor(annotation = WebMvcTest::class)
    val controllers: Array<KClass<*>> = [],
)
