package com.toob.spring_http_client_interfaces.service

import com.toob.spring_http_client_interfaces.client.TodoClient
import com.toob.spring_http_client_interfaces.model.Todo
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Slf4j
@SpringBootTest
class TodoServiceTest {

    @Autowired
    lateinit var todoClient: TodoClient

    @Test
    fun `Variable Tests For This`() {
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