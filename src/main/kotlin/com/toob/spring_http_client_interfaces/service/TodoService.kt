package com.toob.spring_http_client_interfaces.service

import com.toob.spring_http_client_interfaces.client.TodoClient
import com.toob.spring_http_client_interfaces.model.Todo
import org.springframework.stereotype.Service

@Service
class TodoService(private val todoClient: TodoClient) {

    fun fetchTodos(): List<Todo> = todoClient.fetchAll()

    fun addTodo(todo: Todo): Todo = todoClient.post( todo)

    fun modifyTodo(id : Int, todo: Todo): Todo = todoClient.update( id, todo)

}