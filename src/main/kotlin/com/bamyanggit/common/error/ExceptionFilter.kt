package com.bamyanggit.common.error

import com.bamyanggit.common.error.exception.InternalServerErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching { filterChain.doFilter(request, response) }
            .onFailure {
                when (it) {
                    is CustomException -> toErrorResponse(it, response)
                    else -> toErrorResponse(InternalServerErrorException, response)
                }
                it.printStackTrace()
            }
    }

    private fun toErrorResponse(e: CustomException, response: HttpServletResponse) {
        val errorResponse = ErrorResponse(
            status = e.status,
            message = e.message
        )

        response.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            status = response.status
            writer.write(objectMapper.writeValueAsString(errorResponse))
        }
    }
}
