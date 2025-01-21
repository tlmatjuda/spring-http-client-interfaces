package com.toob.spring_http_client_interfaces.model

data class Todo(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val completed: Boolean
)