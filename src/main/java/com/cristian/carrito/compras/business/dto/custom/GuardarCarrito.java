package com.cristian.carrito.compras.business.dto.custom;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuardarCarrito implements Serializable {

	private static final long serialVersionUID = 5353453398488871180L;

	Long usuarioId;

	Long productoId;

	Integer cantidad;

}
