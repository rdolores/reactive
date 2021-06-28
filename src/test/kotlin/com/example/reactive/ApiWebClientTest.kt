package com.example.reactive


import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier


@SpringBootTest
internal class ApiWebClientTest {
    val apiWebClient: ApiWebClient = ApiWebClient()

    @Test
    fun testStepVerifier() {
        StepVerifier.create(apiWebClient.getActivity())
            .expectNextMatches {it.participants >= 1}
            .verifyComplete()
    }
}