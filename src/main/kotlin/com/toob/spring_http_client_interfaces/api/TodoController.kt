package com.toob.spring_http_client_interfaces.api

import com.toob.spring_http_client_interfaces.service.TodoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TodoController(private val todoService: TodoService) {

    @PostMapping("/process")
    fun doTheThings() = todoService.process()

}