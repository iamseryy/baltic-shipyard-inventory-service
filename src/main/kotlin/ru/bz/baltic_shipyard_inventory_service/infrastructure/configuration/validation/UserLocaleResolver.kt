package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.validation

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.LocaleResolver
import java.util.*

class UserLocaleResolver: LocaleResolver {
    override fun resolveLocale(request: HttpServletRequest): Locale =
        Locale.forLanguageTag(request.getHeader("Accept-Language") ?: request.locale.language)

    override fun setLocale(request: HttpServletRequest, response: HttpServletResponse?, locale: Locale?) {
        TODO("Not yet implemented")
    }
}