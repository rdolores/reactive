package com.example.reactive

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class ApiHandlers(val webClient: ApiWebClient) {
    fun activityHandler(): Mono<ServerResponse> = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(webClient.getActivity().log(), ActivityModel::class.java))

    fun properHandler(): Mono<ServerResponse> =
        webClient.getActivity().flatMap {
            ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(it)
        }.switchIfEmpty(ServerResponse.notFound().build())

    fun multipleActivityHandler(req: ServerRequest): Mono<ServerResponse> {
        val num: Int = Integer.valueOf(req.pathVariable("num"))
        return Flux.fromIterable(IntRange(1, num))
            .parallel()
            .flatMap { webClient.getActivity() }
            .ordered { s1, s2 -> s1.activity.compareTo(s2.activity) }
            .collectList()
            .flatMapMany {
                ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }.toMono()
    }


}