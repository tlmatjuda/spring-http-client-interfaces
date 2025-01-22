package com.toob.spring_http_client_interfaces.service

import com.toob.spring_http_client_interfaces.client.TodoClient
import com.toob.spring_http_client_interfaces.model.Todo
import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.*


@SpringBootTest
class TodoClientTest @Autowired constructor(val todoClient: TodoClient) {

    private val log = KotlinLogging.logger {}

    @Test
    fun `Fetch All Todos`() {
        todoClient.fetchAll().also { tasks ->
            assertTrue(tasks.isNotEmpty(), "Tasks should not be empty")
            log.info { "Found ${tasks.size} tasks." }
        }
    }

    @Test
    fun `Find A Task By Id`() {
        todoClient.findById(2).apply {
            assertEquals(2, id ?: error("Task ID should not be null"))
            assertFalse(completed)
            log.info { "Found task: $id with title: $title" }
        }
    }

    @Test
    fun `Test Posting Of A Todo`() {
        val todo = Todo(userId = 1, title = "My New TODO", completed = false)

        assertNull(todo.id)

        todoClient.post(todo).apply {
            assertNotNull(id)
            log.info { "Saved task '$title' with id: $id." }
        }
    }

    @Test
    fun `Update Task Info`() {
        todoClient.findById(3).apply {
            assertNotNull(this)
            val previousTitle = title
            assertEquals("fugiat veniam minus", title)
            assertFalse(completed)

            // Modify task
            title = "Let there be fewer pardons"
            completed = true

            todoClient.update(3, this).apply {
                assertEquals("Let there be fewer pardons", title)
                assertTrue(completed)
                log.info { "Updated task with id: $id." }
                log.info { "Previous title '$previousTitle' updated to '$title'." }
            }
        }
    }

}