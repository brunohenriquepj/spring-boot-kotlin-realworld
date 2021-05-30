package com.example.realworld.annotation

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
annotation class PrototypeScopeComponent(
    @get:AliasFor(annotation = Scope::class)
    val proxyMode: ScopedProxyMode = ScopedProxyMode.INTERFACES
)
