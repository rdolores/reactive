package com.example.reactive

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ApiWebClient() {
    private val webClient: WebClient = WebClient.builder()
        .baseUrl("https://www.boredapi.com/api/")
        .build()

    fun getActivity(): Mono<ActivityModel> =
        webClient.get()
            .uri { it.pathSegment("activity").build()}
            .retrieve()
            .bodyToMono(ActivityModel::class.java)
}