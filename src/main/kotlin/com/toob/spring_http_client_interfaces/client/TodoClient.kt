package com.toob.spring_http_client_interfaces.client

import com.toob.spring_http_client_interfaces.model.Todo
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import org.springframework.web.service.annotation.PutExchange

@HttpExchange
interface TodoClient {

    @GetExchange("/todos")
    fun fetchAll(): List<Todo>

    @PostExchange("/todos")
    fun post(todo: Todo): Todo

    @PutExchange("/todos/{id}")
    fun update(id: Int, todo: Todo): Todo

}