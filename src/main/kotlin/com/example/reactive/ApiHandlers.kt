package com.example.reactive

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

@Component
class ApiHandlers(val webClient: ApiWebClient) {
    fun activityHandler(request: ServerRequest) : Mono<ActivityModel> = webClient.getActivity().log()
}