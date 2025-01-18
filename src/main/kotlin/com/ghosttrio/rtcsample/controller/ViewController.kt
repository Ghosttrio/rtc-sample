package com.ghosttrio.rtcsample.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/home/sse")
    fun ssePage(): String {
        return "sse"
    }

    @GetMapping("/home/websocket")
    fun websocketPage(): String {
        return "websocket"
    }

    @GetMapping("/home/polling")
    fun pollingPage(): String {
        return "polling"
    }
}