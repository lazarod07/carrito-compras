package com.cristian.carrito.compras.business.domain.service;

import com.cristian.carrito.compras.business.domain.persistence.entity.Carrito;
import com.cristian.carrito.compras.business.dto.custom.GuardarCarrito;

public interface CarritoService {
	
	Carrito agregarProducto(GuardarCarrito guardarCarrito);
	
	Boolean eliminarProducto(Long productoId, Long usuarioId);

	Carrito actualizarCantidad(GuardarCarrito guardarCarrito);
}
