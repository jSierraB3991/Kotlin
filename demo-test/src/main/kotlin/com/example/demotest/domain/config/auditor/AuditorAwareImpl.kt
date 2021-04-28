package com.example.demotest.domain.config.auditor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional
import javax.servlet.http.HttpServletRequest

@Component
class AuditorAwareImpl : AuditorAware<String> {

    @Autowired
    private lateinit var httpRequest: HttpServletRequest

    override fun getCurrentAuditor(): Optional<String> {
        return try {
            val type = httpRequest.getHeader("security-type") ?: null
            val uuid = httpRequest.getHeader("security-uuid") ?: null
            val device = httpRequest.getHeader("security-device") ?: null
            val data = mapOf("type" to type, "uuid" to uuid, "device" to device)
            Optional.of(ObjectMapper().writeValueAsString(data))
        } catch (e: IllegalStateException) {
            Optional.of(ObjectMapper().writeValueAsString(mapOf("type" to null, "uuid" to null, "device" to null)))
        }
    }
}
