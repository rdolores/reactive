package com.example.reactive


import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.atomic.AtomicBoolean


@SpringBootTest
internal class ApiWebClientTest() {
    val apiWebClient: ApiWebClient = ApiWebClient()
    private val log = LoggerFactory.getLogger(javaClass)
    @Test
    fun testGetActivity(){
        var taskDone : AtomicBoolean = AtomicBoolean(false)
        apiWebClient.getActivity().doOnSuccess {
            log.info(it.toString())
        }.doOnError {
            log.info("erro na chamada")
        }.doFinally { taskDone.set(true) }
        .subscribe()

        await().untilTrue(taskDone)
    }
}