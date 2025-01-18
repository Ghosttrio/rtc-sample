package com.ghosttrio.rtcsample.controller

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.Executors

@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(WebSocketHandlerImpl(), "/ws")
            .setAllowedOrigins("*")
    }
}

class WebSocketHandlerImpl : TextWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        if (message.payload == "start") {
            sendNumbers(session)
        }
    }

    private fun sendNumbers(session: WebSocketSession) {
        Executors.newSingleThreadExecutor().submit {
            try {
                for (i in 1..10) {
                    val responseMessage = "Data: $i"
                    session.sendMessage(TextMessage(responseMessage))
                    Thread.sleep(1000)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: org.springframework.web.socket.CloseStatus) {
        println("WebSocket connection closed: ${session.id}")
    }
}
