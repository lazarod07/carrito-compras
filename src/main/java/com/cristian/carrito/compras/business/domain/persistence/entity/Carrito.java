package com.cristian.carrito.compras.business.domain.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(schema = "carrito", name = "carrito")
public class Carrito {

	@Schema(description = "Id del carrito")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Usuario dueño del carrito")
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Schema(description = "Lista de productos agregados al carrito")
	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoCarrito> productoCarritos;
	
	@Schema(description = "Total de los agregado al carrito")
	@Transient
	private Double total;

	public Carrito() {
		this.productoCarritos = new ArrayList<>();
	}

	public Double getTotal() {
		return productoCarritos.stream().mapToDouble(ProductoCarrito::getSubtotal).sum();
	}

}
