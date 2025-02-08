package com.toob.spring_http_client_interfaces

import com.toob.spring_http_client_interfaces.client.TodoClient
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.support.RestTemplateAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

@Configuration
class ApplicationFactory(private val restTemplateBuilder: RestTemplateBuilder) {

	@Bean
	fun todoClient(): TodoClient =
		HttpServiceProxyFactory
			.builderFor(RestTemplateAdapter.create(
				restTemplateBuilder.rootUri(BASE_URL).build()
			))
			.build()
			.createClient(TodoClient::class.java)

}


