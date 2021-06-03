package com.example.realworld

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class BeanViewer {
    private val LOG : Logger = LoggerFactory.getLogger(javaClass)

    @EventListener
    fun showBeansRegistered(event: ApplicationReadyEvent) {
        LOG.info("----------------------------------------------------------------")
        LOG.info("Scanned application context beans:")
        /*val apiBeans = event.applicationContext.beanDefinitionNames.filter { it.contains("realworld") }
        for (it in apiBeans) {
            LOG.info("{}", it)
        }*/
        event.applicationContext.beanDefinitionNames.forEach { LOG.info("{}", it) }
        LOG.info("----------------------------------------------------------------")
    }
}
