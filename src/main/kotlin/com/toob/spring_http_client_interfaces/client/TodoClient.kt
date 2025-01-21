package com.toob.spring_http_client_interfaces.client

import com.toob.spring_http_client_interfaces.model.Todo
import org.springframework.web.bind.annotation.*
import org.springframework.web.service.annotation.DeleteExchange
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import org.springframework.web.service.annotation.PutExchange

@HttpExchange
interface TodoClient {

    @GetExchange("/todos")
    fun fetchAll(): List<Todo>

    @GetExchange("/todos/{id}")
    fun findById( @PathVariable id: Int ): Todo

    @PostExchange("/todos")
    fun post( @RequestBody todo: Todo): Todo

    @PutExchange("/todos/{id}")
    fun update( @PathVariable id: Int, @RequestBody todo: Todo): Todo

    @DeleteExchange("/todos/{id}")
    fun remove( @PathVariable id: Int)

}