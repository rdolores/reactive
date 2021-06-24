package com.example.reactive

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ApiHandlers {
    fun helloResponse(request: ServerRequest) : Mono<ServerResponse>  = ServerResponse.ok().bodyValue("Hello\n")
}