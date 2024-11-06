package com.cristian.carrito.compras.business.http.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristian.carrito.compras.business.domain.persistence.entity.Carrito;
import com.cristian.carrito.compras.business.domain.service.CarritoService;
import com.cristian.carrito.compras.business.dto.custom.GuardarCarrito;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/carrito")
public class CarritoComprasController {

	CarritoService carritoService;

	@PostMapping("/agregarProducto")
	public Carrito agregarProducto(@RequestBody(required = true) GuardarCarrito guardarCarrito) {

		return carritoService.agregarProducto(guardarCarrito);
	}

	@DeleteMapping("/{productoId}/{usuarioId}")
	public Boolean eliminarProducto(@PathVariable Long productoId, @PathVariable Long usuarioId) {

		return carritoService.eliminarProducto(productoId, usuarioId);
	}
	
	@PutMapping("/actualizarCantidad")
	public Carrito actualizarCantidad(@RequestBody(required = true) GuardarCarrito guardarCarrito) {
		
		return carritoService.actualizarCantidad(guardarCarrito);
		
	}

}
