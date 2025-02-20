package com.toob.spring_http_client_interfaces

import com.toob.spring_http_client_interfaces.client.TodoClient
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.support.RestTemplateAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class ApplicationFactory(
    private val restTemplateBuilder: RestTemplateBuilder) {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @Bean
    fun todoClient(): TodoClient {
        val restTemplate = restTemplateBuilder.rootUri(BASE_URL).build()
        val restTemplateAdapter = RestTemplateAdapter.create(restTemplate)
        val proxyFactory = HttpServiceProxyFactory.builderFor(restTemplateAdapter).build()

        return proxyFactory.createClient(TodoClient::class.java)
    }

}