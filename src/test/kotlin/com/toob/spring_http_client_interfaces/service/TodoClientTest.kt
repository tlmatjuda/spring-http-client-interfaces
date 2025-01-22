package com.toob.spring_http_client_interfaces.service

import com.toob.spring_http_client_interfaces.client.TodoClient
import com.toob.spring_http_client_interfaces.model.Todo
import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.*

private val log = KotlinLogging.logger {}

@SpringBootTest
class TodoClientTest @Autowired constructor(val todoClient: TodoClient) {


    @Test
    fun `Fetch All Todos`() {
        val tasks = todoClient.fetchAll()
        assertTrue { tasks.isNotEmpty() }
        log.info{"We have found : ${tasks.size} tasks "}
    }

    @Test
    fun `Find A Task By Id`() {
        val task = todoClient.findById(2)
        assertEquals(2, task.id!!)
        assertFalse { task.completed }
        log.info{"Found task : ${task.id} with title : ${task.title} "}
    }

    @Test
    fun `Test Posting Of A Todo`() {
        val todo = Todo(
            userId = 1,
            title = "My New TODO",
            completed = false,
        )

        assertNull(todo.id)
        var savedTask = todoClient.post(todo)

        assertNotNull(savedTask.id)
        log.info{"Saved task : ${savedTask.title} and now has Id : ${savedTask.id} "}
    }

    @Test
    fun `Update Task Info`() {
        val task = todoClient.findById(3)
        assertNotNull(task)
        assertEquals("fugiat veniam minus", task.title)
        assertFalse {  task.completed }
        val previousTitle = task.title

        task.title = "Let there be fewer pardons"
        task.completed = true
        val updatedTask = todoClient.update(3, task)
        assertEquals("Let there be fewer pardons", updatedTask.title)
        assertTrue { updatedTask.completed }

        log.info { "Updated task : ${updatedTask.id}"}
        log.info { "The title : \"${previousTitle}\" actually translates to : \"${updatedTask.title}\"" }
    }

}