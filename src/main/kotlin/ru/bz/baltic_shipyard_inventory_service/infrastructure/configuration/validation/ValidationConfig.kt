package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.validation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver


@Configuration
class ValidationConfig {

    @Bean
    fun messageSource(): ReloadableResourceBundleMessageSource? = ReloadableResourceBundleMessageSource().apply {
                setBasename("classpath:messages")
                setCacheSeconds(3600)
                setDefaultEncoding("UTF-8")
    }

    @Bean
    fun localeResolver(): LocaleResolver? = UserLocaleResolver()
}