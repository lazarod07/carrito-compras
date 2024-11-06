package com.cristian.carrito.compras.business.http.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristian.carrito.compras.business.domain.persistence.entity.Carrito;
import com.cristian.carrito.compras.business.domain.service.CarritoService;
import com.cristian.carrito.compras.business.dto.custom.GuardarCarrito;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/carrito")
@Tag(name = "Carrito de compras", description = "Api para la gestion de carrito de compras")
public class CarritoComprasController {

	CarritoService carritoService;

	@Operation(summary = "Agregar un producto a un carrito de compra de un usuario específico", description = "Retorna el carrito de compras donde se encuenta el producto agregado")
	@PostMapping("/agregarProducto")
	public Carrito agregarProducto(
			@RequestBody(required = true) @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto json que contiene: 'usuarioId' tipo Long,'productoId' tipo Long y 'cantidad' tipo Integer", required = true) GuardarCarrito guardarCarrito) {

		return carritoService.agregarProducto(guardarCarrito);
	}

	@Operation(summary = "Elimina un producto a un carrito de compra de un usuario específico", description = "Retorna 'true' si el producto fue eliminado del carrito de compras exitosamente")
	@DeleteMapping("/{productoId}/{usuarioId}")
	public Boolean eliminarProducto(
			@Parameter(description = "Id del producto a eliminar", required = true) @PathVariable Long productoId,
			@Parameter(description = "Id del usuario a quien le pertenece el carrito") @PathVariable Long usuarioId) {

		return carritoService.eliminarProducto(productoId, usuarioId);
	}

	@Operation(summary = "Actualizar la cantidad de un producto específico del carrito de compras de un usuario específico", description = "Retorna el carrito de compras actualizado")
	@PutMapping("/actualizarCantidad")
	public Carrito actualizarCantidad(
			@RequestBody(required = true) @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto json que contiene: 'usuarioId' tipo Long,'productoId' tipo Long y 'cantidad' tipo Integer", required = true) GuardarCarrito guardarCarrito) {

		return carritoService.actualizarCantidad(guardarCarrito);
	}

	@Operation(summary = "Obtener el carrito de compras de un usuario específico", description = "Retorna el carrito de compras")
	@GetMapping("/ver/{usuarioId}")
	public Carrito ver(
			@Parameter(description = "Id del usuario a quien le pertenece el carrito") @PathVariable Long usuarioId) {
		
		return carritoService.ver(usuarioId);
	}

}
