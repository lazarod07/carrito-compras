package com.cristian.carrito.compras.business.domain.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cristian.carrito.compras.business.domain.persistence.entity.Carrito;
import com.cristian.carrito.compras.business.domain.persistence.entity.Producto;
import com.cristian.carrito.compras.business.domain.persistence.entity.ProductoCarrito;
import com.cristian.carrito.compras.business.domain.persistence.entity.Usuario;
import com.cristian.carrito.compras.business.domain.persistence.repository.CarritoRepository;
import com.cristian.carrito.compras.business.domain.persistence.repository.ProductoRepository;
import com.cristian.carrito.compras.business.domain.persistence.repository.UsuarioRepository;
import com.cristian.carrito.compras.business.domain.service.CarritoService;
import com.cristian.carrito.compras.business.dto.custom.GuardarCarrito;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarritoServiceImpl implements CarritoService {

	private CarritoRepository carritoRepository;

	private UsuarioRepository usuarioRepository;

	private ProductoRepository productoRepository;

	@Transactional
	@Override
	public Carrito agregarProducto(GuardarCarrito guardarCarrito) {

		Integer cantidad = guardarCarrito.getCantidad();

		if (cantidad == 0)
			new RuntimeException("La cantida no puede ser cero");

		Usuario usuario = usuarioRepository.findById(guardarCarrito.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Producto producto = productoRepository.findById(guardarCarrito.getProductoId())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		Optional<Carrito> optionalCarrito = carritoRepository.findByUsuario(usuario);

		Carrito carrito;

		if (optionalCarrito.isPresent()) {
			carrito = optionalCarrito.get();

			if (carrito.getProductoCarritos().stream().anyMatch(pc -> pc.getProducto() == producto)) {
				carrito.getProductoCarritos().stream().filter(pc -> pc.getProducto() == producto)
						.forEach(pc -> pc.setCantida(pc.getCantida() + cantidad));
			} else {
				agregarNuevoProductoList(carrito, cantidad, producto);
			}

		} else {
			carrito = new Carrito();
			carrito.setUsuario(usuario);

			agregarNuevoProductoList(carrito, cantidad, producto);
		}

		return carritoRepository.save(carrito);
	}

	@Transactional
	@Override
	public Boolean eliminarProducto(Long productoId, Long usuarioId) {

		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Carrito carrito = carritoRepository.findByUsuario(usuario)
				.orElseThrow(() -> new RuntimeException("No hay carrito"));

		carrito.getProductoCarritos().removeIf(pc -> pc.getProducto() == producto);

		if (carrito.getProductoCarritos().size() == 0) {
			carritoRepository.delete(carrito);
		} else {
			carritoRepository.save(carrito);
		}

		return Boolean.TRUE;
	}

	public void agregarNuevoProductoList(Carrito carrito, Integer cantidad, Producto producto) {
		ProductoCarrito productoCarrito = new ProductoCarrito();
		productoCarrito.setCantida(cantidad);
		productoCarrito.setProducto(producto);
		productoCarrito.setCarrito(carrito);
		carrito.getProductoCarritos().add(productoCarrito);
	}

	@Override
	public Carrito actualizarCantidad(GuardarCarrito guardarCarrito) {

		Usuario usuario = usuarioRepository.findById(guardarCarrito.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Producto producto = productoRepository.findById(guardarCarrito.getProductoId())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		Carrito carrito = carritoRepository.findByUsuario(usuario)
				.orElseThrow(() -> new RuntimeException("No hay carrito"));

		if (carrito.getProductoCarritos().stream().anyMatch(pc -> pc.getProducto() == producto)) {

			if (guardarCarrito.getCantidad() == 0) {

				carrito.getProductoCarritos().removeIf(pc -> pc.getProducto() == producto);

				if (carrito.getProductoCarritos().size() == 0) {
					carritoRepository.delete(carrito);
					return null;
				}

			} else {
				carrito.getProductoCarritos().stream().filter(pc -> pc.getProducto() == producto)
						.forEach(pc -> pc.setCantida(guardarCarrito.getCantidad()));
			}

		} else {
			new RuntimeException("Ese producto no existe en el carrito");
		}

		return carritoRepository.save(carrito);
	}

}
