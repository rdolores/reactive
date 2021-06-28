package com.example.reactive

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class ApiRouter {
    @Bean
    fun mainRoute(handler: ApiHandlers) = router {
            GET("/{num}") { handler.multipleActivityHandler(it) }
            GET("/") { handler.properHandler() }
        }
}