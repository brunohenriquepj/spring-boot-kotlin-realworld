package com.example.realworld.annotation

import com.example.realworld.constants.ScopeConstant
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope(value = ScopeConstant.Request, proxyMode = ScopedProxyMode.INTERFACES)
annotation class RequestScopeComponent
