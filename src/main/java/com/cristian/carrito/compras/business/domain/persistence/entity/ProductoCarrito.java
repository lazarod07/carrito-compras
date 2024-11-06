package com.cristian.carrito.compras.business.domain.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(schema = "carrito", name = "producto_carrito")
public class ProductoCarrito {

	@Schema(description = "Id de la reación Producto-Carrito")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Id del producto")
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@Schema(description = "Id del carrito")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;

	@Schema(description = "Udidades del producto en el carrito")
	@Column(name = "cantidad")
	private Integer cantida;

	@Schema(description = "Subtotal, número de productos multiplicados por el precio")
	@Transient
	private Double subTotal;

	public Double getSubtotal() {
		return producto.getPrecio() * cantida;
	}
}
