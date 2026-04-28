package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.security

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class, ExpiredJwtException::class)
    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        // �������� ����� �� ���������



        val token = jwtService.getTokenWithoutBearer(request)
        if (token == null) {
            filterChain.doFilter(request, response)
            return
        }

        val username = jwtService.extractUsername(token)


        if (username.isNotEmpty() && SecurityContextHolder.getContext().authentication == null) {
            // ���� ����� �������, �� ��������������� ������������
            if (jwtService.verifyToken(token)) {
                val context = SecurityContextHolder.createEmptyContext()

                val authToken = UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    null,
                )

                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                context.authentication = authToken
                SecurityContextHolder.setContext(context)
            }
        }
        filterChain.doFilter(request, response)

    }
}