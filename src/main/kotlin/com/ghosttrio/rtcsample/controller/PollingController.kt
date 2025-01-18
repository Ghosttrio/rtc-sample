package com.ghosttrio.rtcsample.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PollingController {

    private var count: Int = 0

    @GetMapping("/polling")
    fun loadPollingServer(): Int {
        val result = count++
        return result
    }
}