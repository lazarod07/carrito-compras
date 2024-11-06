package com.cristian.carrito.compras.business.domain.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;

	@Column(name = "cantidad")
	private Integer cantida;

	@Transient
	private Double subTotal;

	public Double getSubtotal() {
		return producto.getPrecio() * cantida;
	}
}
