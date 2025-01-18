package com.ghosttrio.rtcsample.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.Executors

@RestController
class SSEController {

    @GetMapping("/sse")
    fun loadSSEServer(): SseEmitter {
        val emitter = SseEmitter()

        val executorService = Executors.newSingleThreadExecutor()

        executorService.submit {
            try {
                for (i in 0..10) {
                    val data = SseEmitter.event().name("message").data("Data $i")
                    emitter.send(data)
                    Thread.sleep(1000)
                }
            } catch (e: Exception) {
                emitter.completeWithError(e)
            }
        }
        return emitter
    }
}