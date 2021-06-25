package com.example.reactive

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters.fromPublisher
import org.springframework.web.reactive.function.server.router


@Configuration
class ApiRouter {
    @Bean
    fun mainRoute(handler: ApiHandlers) = router {
            GET("/") { ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(handler.activityHandler(it), ActivityModel::class.java)) }
        }
}