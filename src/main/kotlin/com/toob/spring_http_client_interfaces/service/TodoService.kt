package com.toob.spring_http_client_interfaces.service

import com.toob.spring_http_client_interfaces.client.TodoClient
import com.toob.spring_http_client_interfaces.model.Todo
import org.springframework.stereotype.Service

@Service
class TodoService(private val todoClient: TodoClient) {

    fun fetchTodos(): List<Todo> = todoClient.fetchAll()

    fun save(todo: Todo): Todo = todoClient.post( todo)

    fun updateTodo(id : Int, todo: Todo): Todo = todoClient.update( id, todo)

    fun delete(id : Int) = todoClient.remove( id)

    fun process() {
        val todo = Todo(
            userId = 1,
            title = "My New TODO",
            completed = false,
        )

        var save = todoClient.post(todo)
        println("POSTED Record with ID : ${save.id}")
        println(save.toString())

        val CONTEXT_ID = 2
        val found = todoClient.findById(CONTEXT_ID)
        found.title = "Updated My TODO, Now!"
        found.completed = true

        val updated = todoClient.update(CONTEXT_ID, found)
        println("UPDATED Record with ID : ${updated.id}")
        println(updated.toString())

        todoClient.remove( found.id!!)
        println("ALL RECORDS PROCESSED")
    }


}