package com.example.reactive

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class ApiHandlers(val webClient: ApiWebClient) {
    fun activityHandler() : Mono<ServerResponse> = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(webClient.getActivity().log(), ActivityModel::class.java))

    fun properHandler() : Mono<ServerResponse> =
        webClient.getActivity().flatMap {
            ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(it)
        }.switchIfEmpty(ServerResponse.notFound().build())

}