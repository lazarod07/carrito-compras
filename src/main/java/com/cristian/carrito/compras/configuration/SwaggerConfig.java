package com.cristian.carrito.compras.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API Carrrito de compras").version("1.0")
				.description("Documentación de la API de Carrito de compras"));
	}

}
