package com.example.realworld

import com.example.realworld.annotation.RequestScopeComponent
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

// https://www.baeldung.com/spring-componentscan-filter-type#filtertypeassignabletype
@ComponentScan(includeFilters = [ComponentScan.Filter(
    type = FilterType.ANNOTATION,
    classes = [RequestScopeComponent::class]
)])
@Configuration
class ComponentScanAnnotationFilterApp
