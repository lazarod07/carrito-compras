package com.cristian.carrito.compras.business.domain.persistence.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "carrito", name = "producto")
public class Producto {

	@Schema(description = "Id del producto")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Nombre del producto")
	@Column(name = "nombre")
	private String nombre;

	@Schema(description = "Precio del producto")
	private Double precio;

}
