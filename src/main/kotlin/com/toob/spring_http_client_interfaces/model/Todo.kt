package com.toob.spring_http_client_interfaces.model

data class Todo(
    var id: Int? = null,
    var userId: Int,
    var title: String,
    var completed: Boolean = false,
)
